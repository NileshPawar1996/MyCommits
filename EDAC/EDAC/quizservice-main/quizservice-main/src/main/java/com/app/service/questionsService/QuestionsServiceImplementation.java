//CREATED BY RAJVARDHAN SHINDE
package com.app.service.questionsService;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.QuestionsDao;
import com.app.pojos.Question;

@Service
@Transactional
public class QuestionsServiceImplementation implements QuestionsService{
	
	@Autowired
	private QuestionsDao questionsDao;
	@Override
	public void addQuestion(Question question) {
		questionsDao.save(question);
	}
	@Override
	public List<Question> getQuestionsByTopic(Long id) {
		return questionsDao.findByTopicId(id);
		
	}
	@Override
	public List<Question> getQuestionsByTopicAndModule(Long topicId, Long moduleId) {
		return questionsDao.findByTopicAndModule(topicId, moduleId);
		
	}
	@Override
	public List<Question> getQuestionsByModule(Long id) {
		return questionsDao.findByModule(id);
	}
	@Override
	public void deleteQuestion(Long questionId) {
		questionsDao.deleteById(questionId);
		
	}



}
