//CREATED BY RAJVARDHAN SHINDE
package com.app.service.examsService;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.ExamDao;
import com.app.pojos.Exam;
@Service
@Transactional
public class ExamsService {
	@Autowired
	private ExamDao examDao;

	public void addExam(Exam exam) {
		examDao.save(exam);
		
	}
	public void deleteExam(Long examId) {
		if(examDao.existsById(examId))
		examDao.deleteById(examId);
		else throw new ResourceNotFoundException("Exam by id = "+examId+" does not exist");
		
	}
	public Exam findById(Long id) {
		Exam exam = examDao.findById(id).orElse(null);
		if(exam == null) {
			throw new ResourceNotFoundException("The Exam with id = "+id + " not found");
		}else return exam;
	}
	public List<Exam> getExamsDesc(){
		return examDao.findAllExams();
	}
	public Exam getExamById(Long id) {
		return examDao.findById(id).orElse(null);
	}
	public List<Exam> getByBatch(Long batchId){
		return examDao.findByBatch(batchId);
	}
	public List<Exam> getExamsBeforeStartDesc(){
		return examDao.findAllExamsBeforeStart();
	}

	
}
