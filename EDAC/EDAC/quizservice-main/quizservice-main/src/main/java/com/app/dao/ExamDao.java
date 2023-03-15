//CREATED BY RAJVARDHAN SHINDE
package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.pojos.Exam;

public interface ExamDao extends JpaRepository<Exam, Long>{
	@Query(value = "SELECT * FROM exams ORDER BY start_date_time DESC LIMIT 10", nativeQuery = true)
	List<Exam> findAllExams();
	
	@Query(value = "SELECT * FROM exams WHERE fk_batch_id = ?1 ORDER BY start_date_time DESC", nativeQuery = true)
	List<Exam> findByBatch(Long batchId);
	
	@Query(value = "Select * from exams where start_date_time > CURRENT_TIMESTAMP ORDER BY start_date_time DESC", nativeQuery = true)
	List<Exam> findAllExamsBeforeStart();
}
