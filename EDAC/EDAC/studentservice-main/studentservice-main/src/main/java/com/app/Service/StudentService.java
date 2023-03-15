package com.app.Service;

import java.util.List;

import com.app.pojos.Student;

public interface StudentService {
	public Student getStudentByStudentId(Long studentId);
	
	public List<Student> getStudentsByBatchId(Long batchId);
	
	public List<Student> getStudentsByCourseId(String CourseId);
	
	public Student insertStudent(Student student);
	
	public List<Student> insertStudentList(List<Student> listOfStudents);
	
	public Student editStudent(Student editedStudent);
	
	public Student deleteStudent(Long studentId);



}
