package com.app.Service;
// by AdityaS
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.daos.StudentDao;
import com.app.pojos.Student;
@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	StudentDao studentDao;
	
	@Override
	public Student getStudentByStudentId(Long studentId) {
		return studentDao.findStudenytById(studentId);
	}

	@Override
	public List<Student> getStudentsByBatchId(Long batchId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getStudentsByCourseId(String CourseId) {
		return studentDao.findStudentsByCourseId(CourseId);
	}

	@Override
	public Student insertStudent(Student student) {
		System.out.println(student);
		return studentDao.save(student);
		
	}

	@Override
	public List<Student> insertStudentList(List<Student> listOfStudents) {
		return studentDao.saveAll(listOfStudents);		 
	}


	@Override
	public Student deleteStudent(Long studentId) {
		
		return null;
	}

	@Override
	public Student editStudent(Student editedStudent) {
		// TODO Auto-generated method stub
		return null;
	}

}
