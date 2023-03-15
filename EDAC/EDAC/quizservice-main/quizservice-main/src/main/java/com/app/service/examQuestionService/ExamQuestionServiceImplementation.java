//CREATED BY RAJVARDHAN SHINDE
package com.app.service.examQuestionService;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.ExamQuestionsDao;
import com.app.dao.QuestionsDao;
import com.app.pojos.Exam;
import com.app.pojos.ExamQuestion;
import com.app.pojos.Question;
import com.app.service.examsService.ExamsService;
import com.app.service.questionsService.QuestionsService;
@Service
@Transactional
public class ExamQuestionServiceImplementation {
	
	@Autowired
	private ExamQuestionsDao examQuestionsDao;
	@Autowired
	private ExamsService examService;
	@Autowired
	private QuestionsDao queDao;

	public void addExamQuestion(ExamQuestion examQuestion) {
		examQuestionsDao.save(examQuestion);
		
	}


	public void deleteExamQuestion(Long examQuestionId) {
		examQuestionsDao.deleteById(examQuestionId);	
	}
	
	public List<ExamQuestion> getExamQuestionByExamId(Long examId){
		return examQuestionsDao.findByExamId(examId);
		
	}

	public void deleteExamQuestionByExamIdAndModuleId(Long exam, Long question) {
		examQuestionsDao.deleteByExamIdAndQuestionId(exam, question);
		
	}

}
