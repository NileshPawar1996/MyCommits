//CREATED BY RAJVARDHAN SHINDE
package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.pojos.Exam;
import com.app.pojos.ExamQuestion;
import com.app.pojos.Question;

public interface ExamQuestionsDao extends JpaRepository<ExamQuestion, Long>{
	List<ExamQuestion> findByExamId(Long id);
	@Modifying
//	@Query("delete from ExamQuestion e where e.exam=:exam and e.question=:question")
	void deleteByExamIdAndQuestionId(Long exam, Long question);
	
}
