package com.app.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;	
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.app.dtos.teacher.GetCourseBatchModuleByTeacherId;
import com.app.pojos.Staff;
import com.app.service.TeacherService;
import com.app.utils.Message;


@RestController
@RequestMapping("/teacher")
public class TeacherController {
	
	@Autowired
	TeacherService teacherService;
	
	@PostMapping("/add")
	public ResponseEntity<Object> addTeacher(@RequestBody Staff teacher){
		try {
			System.out.println(teacher);
			teacherService.addTeacher(teacher);
			return Message.getSuccessMessage("Teacher Created Successfully", HttpStatus.CREATED);
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Object> getTeacher(@PathVariable Long id){
		try {
			Staff teacher = teacherService.findByTeacherId(id);
			if(teacher != null) {
				return Message.getSuccessMessage(teacher, HttpStatus.ACCEPTED);
			}
			else {
				return Message.getRuntimeErrorMessage("Teacher Not Found", HttpStatus.NOT_FOUND);
			}
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<Object> getAllTeachers(){
		try {
			return Message.getSuccessMessage(teacherService.getAllTeachers(), HttpStatus.CREATED);
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<Object> editTeacher(@PathVariable Long id, @RequestBody Staff teacher){
		try {
			String functionStatus = teacherService.editTeacher(id, teacher);
			if(functionStatus.equals("success")) {
				return Message.getSuccessMessage("Teacher Updated Successfully", HttpStatus.CREATED);
			}
			else
				return Message.getRuntimeErrorMessage("Please Enter Valid Data", HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteTeacher(@PathVariable Long id){
		try {
			String functionStatus = teacherService.deleteTeacher(id);
			if(functionStatus.equals("success")) {	
				return Message.getSuccessMessage("Teacher Deleted Successfully", HttpStatus.CREATED);
			}
			else
				return Message.getRuntimeErrorMessage("Cannot Delete Teacher", HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/get/questions/module/{id}")
	public String getQuestionsListByModuleId(@PathVariable Long id) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity <String> entity = new HttpEntity<String>(headers);
		  
		return restTemplate.exchange("192.168.175.17:8080/quiz/question/module/1", HttpMethod.GET, entity, String.class).getBody();
   }

	@GetMapping("/batches_by_teacher/{id}")
	public ResponseEntity<Object> getBatchesOfTeacher(@PathVariable Long id) {
		try {
			List<Long> returnObj = teacherService.getBatchesOfTeacher(id);
			if(returnObj != null) {	
				return Message.getSuccessMessage(returnObj, HttpStatus.CREATED);
			}
			else
				return Message.getRuntimeErrorMessage("Batches Not Found", HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
   }
	
	@GetMapping("/courses_by_teacher/{id}")
	public ResponseEntity<Object> getCoursesOfTeacher(@PathVariable Long id) {
		try {
			List<String> returnObj = teacherService.getCoursesOfTeacher(id);
			if(returnObj != null) {	
				return Message.getSuccessMessage(returnObj, HttpStatus.CREATED);
			}
			else
				return Message.getRuntimeErrorMessage("Course Not Found", HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
   }
	
	@GetMapping("/modules_by_teacher/{batchId}/{teacherId}")	
	public ResponseEntity<Object> getCoursesOfTeacher(@PathVariable Long batchId, @PathVariable Long teacherId) {
		try {
			List<Long> returnObj = teacherService.getModulesOfTeacher(batchId, teacherId);
			if(returnObj != null) {	
				return Message.getSuccessMessage(returnObj, HttpStatus.CREATED);
			}
			else
				return Message.getRuntimeErrorMessage("Course Not Found", HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
   }
	
}
