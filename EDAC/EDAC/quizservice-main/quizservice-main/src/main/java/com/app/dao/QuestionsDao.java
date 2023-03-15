//CREATED BY RAJVARDHAN SHINDE
package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.pojos.Question;

public interface QuestionsDao extends JpaRepository<Question, Long> {
	
	List<Question> findByTopicId(Long id);
	@Query(value = "SELECT * FROM questions WHERE fk_module_id = ?1", nativeQuery = true)
	List<Question> findByModule(Long id);
	@Query(value = "SELECT * FROM questions WHERE fk_topic_id = ?1 and fk_module_id = ?2", nativeQuery = true)
	List<Question> findByTopicAndModule(Long topicId, Long moduleId);

}
