package com.app.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Student;

public interface StudentDao extends JpaRepository<Student, Long> {
	public List<Student> findStudentsByBatchId(Long batchId);
	public List<Student> findStudentsByBatchIdAndCourseId(Long batchId, Long courseId);
	public List<Student> findStudentsByCourseId(String courseId);
	public Student findStudenytById(Long studentId);
}
