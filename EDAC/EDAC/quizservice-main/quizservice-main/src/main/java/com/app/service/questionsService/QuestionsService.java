//CREATED BY RAJVARDHAN SHINDE
package com.app.service.questionsService;

import java.util.List;

import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.pojos.Question;

public interface QuestionsService {
	List<Question> getQuestionsByTopic(Long id);
	List<Question> getQuestionsByTopicAndModule(Long topicId, Long moduleId);
	List<Question> getQuestionsByModule(Long id);
	void addQuestion(Question question);
	void deleteQuestion(Long questionId);
}
