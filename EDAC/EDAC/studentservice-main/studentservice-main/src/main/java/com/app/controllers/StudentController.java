package com.app.controllers;
import java.util.ArrayList;
import java.util.List;

// By AdityaS
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Service.StudentService;
import com.app.dtos.StudentDTO;
import com.app.pojos.Student;
import com.app.utils.Message;

@RequestMapping("/student")
@RestController
@CrossOrigin
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	//Get a student by Id
	
	@GetMapping("/{studentId}")
	public ResponseEntity<Object> getStudentById(@PathVariable Long studentId){
		try {
			Student foundStudent = studentService.getStudentByStudentId(studentId);
			if(foundStudent!=null) {
				StudentDTO returnDto = new StudentDTO();
				
				returnDto.setAadharNo(foundStudent.getAadharNo());
				returnDto.setBatchId(foundStudent.getBatchId());
				returnDto.setCompanyDetails(foundStudent.getCompanyDetails());
				returnDto.setContactDetails(foundStudent.getContactDetails());
				returnDto.setCourseId(foundStudent.getCourseId());
				returnDto.setDob(foundStudent.getDob());
				returnDto.setEmail(foundStudent.getEmail());
				returnDto.setFirstName(foundStudent.getFirstName());
				returnDto.setId(foundStudent.getId());
				returnDto.setLastName(foundStudent.getLastName());
				returnDto.setOverallMarks(foundStudent.getOverallMarks());
				returnDto.setPhotoUrl(foundStudent.getPhotoUrl());
	
				return Message.getSuccessMessage(returnDto, HttpStatus.OK);
			}else
				return Message.getRuntimeErrorMessage("Student not found", HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	//Insert a student
	
	@PostMapping("/add")
	public ResponseEntity<Object> insertStudent(@RequestBody Student student){
		try {
			studentService.insertStudent(student);
			return Message.getSuccessMessage("Student added successfully", HttpStatus.OK);
		}catch(Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	//Edit a student
	@PutMapping("/edit")
	public ResponseEntity<Object> editStudent(@RequestBody Student student){
		try {
			studentService.insertStudent(student);
			return Message.getSuccessMessage("Student added successfully", HttpStatus.OK);
		}catch(Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	//Insert List of students
	
	@PostMapping("/addAll")
	public List<Student> insertAllStudent(@RequestBody List<Student> students){
//		try {
			return studentService.insertStudentList(students);
//			return Message.getSuccessMessage("Students added successfully", HttpStatus.OK);
//		}catch(Exception e) {
//			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
		
	}
	
	
	
	@GetMapping("/course/{courseId}")
	public ResponseEntity<Object> getStudentsByCourseId(@PathVariable String courseId){
		try {
			List<Student> foundStudents = studentService.getStudentsByCourseId(courseId);
			if(foundStudents!=null) {
				List<StudentDTO> returnList = new ArrayList<>();
				for(Student foundStudent: foundStudents) {
					StudentDTO returnDto = new StudentDTO();
					
					returnDto.setAadharNo(foundStudent.getAadharNo()); //putting values  inside Students DTO and creating a new List to return
					returnDto.setBatchId(foundStudent.getBatchId());
					returnDto.setCompanyDetails(foundStudent.getCompanyDetails());
					returnDto.setContactDetails(foundStudent.getContactDetails());
					returnDto.setCourseId(foundStudent.getCourseId());
					returnDto.setDob(foundStudent.getDob());
					returnDto.setEmail(foundStudent.getEmail());
					returnDto.setFirstName(foundStudent.getFirstName());
					returnDto.setId(foundStudent.getId());
					returnDto.setLastName(foundStudent.getLastName());
					returnDto.setOverallMarks(foundStudent.getOverallMarks());
					returnDto.setPhotoUrl(foundStudent.getPhotoUrl());
					
					returnList.add(returnDto);
				}
				
				return Message.getSuccessMessage(returnList, HttpStatus.OK);
			}else
				return Message.getRuntimeErrorMessage("Student not found", HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
