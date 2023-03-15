package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.pojos.Exam;
import com.app.pojos.StudentExamAnswer;

public interface StudentExamAnswerDao extends JpaRepository<StudentExamAnswer, Long>{
	@Query(value = "SELECT * FROM student_exam_answers WHERE fk_student_id = ?1 and fk_exam_id = ?2", nativeQuery = true)
	List<StudentExamAnswer> findByStudentIdAndExamId(Long studentId, Long ExamId);
	
	@Query(value = "SELECT * FROM student_exam_answers WHERE fk_student_id = ?1", nativeQuery = true)
	List<StudentExamAnswer> findAllAttemptedExamQuestions(Long studentId);
}
