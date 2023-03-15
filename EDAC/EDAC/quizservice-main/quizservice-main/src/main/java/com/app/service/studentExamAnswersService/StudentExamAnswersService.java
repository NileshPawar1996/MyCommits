package com.app.service.studentExamAnswersService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.StudentExamAnswerDao;
import com.app.dto.DataDTO;
import com.app.dto.ExamDetailsDto;
import com.app.dto.ModuleDTO;
import com.app.pojos.Exam;
import com.app.pojos.ExamQuestion;
import com.app.pojos.ExamType;
import com.app.pojos.Question;
import com.app.pojos.StudentExamAnswer;
import com.app.pojos.Topic;

@Service
@Transactional
public class StudentExamAnswersService {
    @Value("${localManagement.api.url}")
	private String management;
	@Autowired
	StudentExamAnswerDao seaDao;
	ExamDetailsDto dto = new ExamDetailsDto();

	public void addStudentExamAnswer(StudentExamAnswer sea) {
		seaDao.save(sea);
	}

	public void addAllStudentExamAnswers(List<StudentExamAnswer> sList) {
		seaDao.saveAll(sList);
	}

	public List<StudentExamAnswer> getByStudentAndExam(Long studentId, Long examId) {
		return seaDao.findByStudentIdAndExamId(studentId, examId);
	}

	public Object getAllExamDetails(Long studentId) {
		List<StudentExamAnswer> seaList = seaDao.findAllAttemptedExamQuestions(studentId);
		// find distinct exams
		HashSet<Long> distinctExams = new HashSet<Long>();
		for (int i = 0; i < seaList.size(); i++) {
			distinctExams.add(seaList.get(i).getExam().getId());
		}
		Iterator<Long> it = distinctExams.iterator();

		HashSet<ExamDetailsDto> examDetails = new HashSet<ExamDetailsDto>();
		while (it.hasNext()) {
			int totalQuestions = 0;
			int marks = 0;
			Long batchId = 0L;
			Long moduleId = 0L;
			LocalDateTime endDateTime = null ;
			LocalDateTime startDateTime = null;
			ExamType type = null;
			Long examId = it.next();
			// find total questions by exam id
			for (int i = 0; i < seaList.size(); i++) {
				if (seaList.get(i).getExam().getId() == examId) {
					totalQuestions++;
					if (seaList.get(i).getSelectedAnswer() == seaList.get(i).getQuestion().getAnswer()) {
						marks++;
					}
				}
			}
			for (int i = 0; i < seaList.size(); i++) {
				Exam e = seaList.get(i).getExam();
				if (e.getId() == examId) {
					batchId = e.getBatchId();
					moduleId = e.getModuleId();
					endDateTime = e.getEndDateTime();
					startDateTime = e.getStartDateTime();
					type = e.getType();
				}
			}
			// get count of correct questions for an exam
			int percentage = ((marks * 100) / totalQuestions);
			
			RestTemplate restTemplate = new RestTemplate();

			String url = management+"module/"+moduleId;
			ResponseEntity<DataDTO> response = restTemplate.getForEntity(url, DataDTO.class);
			if (response.getStatusCode() == HttpStatus.OK) {
				DataDTO data= response.getBody();
			    ModuleDTO module = data.getData();
				dto.setModule(module);
			} else {
			    throw new ResourceNotFoundException("module with id = "+moduleId+" not found");
			}
			
			dto.setExamId(examId);
			dto.setTotalQuestions(totalQuestions);
			dto.setCorrectQuestions(marks);
			dto.setPercentage(percentage);
			dto.setBatchId(batchId);
			dto.setEndDateTime(endDateTime);
			dto.setType(type);
			examDetails.add(dto);
			dto = new ExamDetailsDto();
		}
		return examDetails;
	}
	
	public Object getModuleWiseAnalysis(Long studentId) {
		List<StudentExamAnswer> seaList = seaDao.findAllAttemptedExamQuestions(studentId);
		// find distinct exams
		HashSet<Long> distinctModules = new HashSet<Long>();
		for (int i = 0; i < seaList.size(); i++) {
			distinctModules.add(seaList.get(i).getQuestion().getModuleId());
		}
		Iterator<Long> it = distinctModules.iterator();
		HashMap<String, Object> moduleWiseDetails = new HashMap<String, Object>();
		List<HashMap<String,Object>> mList = new ArrayList<HashMap<String,Object>>();
		while (it.hasNext()) {
			Long moduleId = it.next();
			RestTemplate restTemplate = new RestTemplate();
			String url = management+"module/"+moduleId;
			ResponseEntity<DataDTO> response = restTemplate.getForEntity(url, DataDTO.class);
			if (response.getStatusCode() == HttpStatus.OK) {
				DataDTO data= response.getBody();
			    ModuleDTO module = data.getData();
				moduleWiseDetails.put("moduleName", module.getModuleName());
			} else {
			    throw new ResourceNotFoundException("module with id = "+moduleId+" not found");
			}
			moduleWiseDetails.put("moduleId", moduleId);
			ArrayList<StudentExamAnswer> moduleQuestion = new ArrayList<StudentExamAnswer>();
			for(int i =0; i<seaList.size(); i++) {
				if(seaList.get(i).getQuestion().getModuleId() == moduleId) {
					moduleQuestion.add(seaList.get(i));
				}
			}
			moduleWiseDetails.put("totalQuestions", moduleQuestion.size());
			int marks =0;
			for(int i =0; i<moduleQuestion.size(); i++) {
				if(moduleQuestion.get(i).getQuestion().getAnswer() == moduleQuestion.get(i).getSelectedAnswer()) {
					marks++;
				}
			}
			moduleWiseDetails.put("correctAnswers", marks);
			int percentage = (marks*100)/moduleQuestion.size();
			moduleWiseDetails.put("percentage", percentage);
			mList.add(moduleWiseDetails);
		}
		return mList;
	}
	
	public Object getTopicWiseAnalysis(Long studentId, Long moduleId) {
		List<StudentExamAnswer> seaList = seaDao.findAllAttemptedExamQuestions(studentId);
		HashMap<Long, Topic> distinctTopics = new HashMap<Long, Topic>();
		for (int i = 0; i < seaList.size(); i++) {
			if(seaList.get(i).getQuestion().getModuleId() == moduleId) {
				distinctTopics.put(seaList.get(i).getQuestion().getTopic().getId(), seaList.get(i).getQuestion().getTopic());			
			}
		}
		List<HashMap<String,Object>> tList = new ArrayList<HashMap<String,Object>>();
        for (Long topicId: distinctTopics.keySet()) {
        	HashMap<String, Object> topicWiseAnalysis = new HashMap<String, Object>();
        	topicWiseAnalysis.put("TopicId", topicId);
        	topicWiseAnalysis.put("TopicName", distinctTopics.get(topicId).getTopicName());
        	//get questionsList topicWise
        	ArrayList<StudentExamAnswer> topicQuestions = new ArrayList<StudentExamAnswer>();
        	for(int i = 0; i<seaList.size(); i++) {
        		if(seaList.get(i).getQuestion().getTopic().getId() == topicId) {
        			topicQuestions.add(seaList.get(i));
        		}
        	}
        	topicWiseAnalysis.put("totalQuestions", topicQuestions.size());
        	int marks = 0;
        	for(int i =0 ;i<topicQuestions.size(); i++) {
        		if(topicQuestions.get(i).getSelectedAnswer() == topicQuestions.get(i).getQuestion().getAnswer()) {
        			marks++;
        		}
        	}
        	topicWiseAnalysis.put("correctAnswers", marks);
        	topicWiseAnalysis.put("percentage", (marks*100)/topicQuestions.size());
        	tList.add(topicWiseAnalysis);
        }
            
		return tList;
	}
	
	public Object getAllAttemptedModules(Long studentId) {
		List<StudentExamAnswer> seaList = seaDao.findAllAttemptedExamQuestions(studentId);
		HashSet<Long> distinctModules = new HashSet<Long>();
		for (int i = 0; i < seaList.size(); i++) {
			distinctModules.add(seaList.get(i).getQuestion().getModuleId());
		}
		List<ModuleDTO> mList = new ArrayList<ModuleDTO>();
		Iterator<Long> it = distinctModules.iterator();
		while(it.hasNext()) {
			Long moduleId = it.next();
			RestTemplate restTemplate = new RestTemplate();
			String url = management+"module/"+moduleId;
			ResponseEntity<DataDTO> response = restTemplate.getForEntity(url, DataDTO.class);
			if (response.getStatusCode() == HttpStatus.OK) {
				DataDTO data= response.getBody();
			    ModuleDTO module = data.getData();
			    mList.add(module);
			    
			} else {
			    throw new ResourceNotFoundException("module with id = "+moduleId+" not found");
			}
		}
		return mList;
	}
		
}
