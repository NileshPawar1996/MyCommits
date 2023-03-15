package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.module.StatusAndTeacherNameDto;
import com.app.pojos.Course;
import com.app.service.CourseService;
import com.app.utils.Message;

@CrossOrigin
@RestController
@RequestMapping("/course")
public class CourseController {
	@Autowired
	CourseService courseService;
	
	@PostMapping("/add")
	public ResponseEntity<Object> addCourse(@RequestBody Course course){
		try {
			return Message.getSuccessMessage(courseService.addCourse(course), HttpStatus.CREATED);
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<Object> getAllCourses(){
		try {
			return Message.getSuccessMessage(courseService.getAllCourses(), HttpStatus.OK);
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/modules/status/{id}/{batchId}")
	public ResponseEntity<Object> getStatusOfAllModuleCompletion(@PathVariable String id, @PathVariable Long batchId){
		try {
			List<StatusAndTeacherNameDto> statusOfAllModules = courseService.getStatusOfAllModuleCompletion(id, batchId);
			if(statusOfAllModules != null)
				return Message.getSuccessMessage(statusOfAllModules, HttpStatus.OK);
			else
				return Message.getRuntimeErrorMessage("Invaild", HttpStatus.NOT_FOUND);
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getCourse(@PathVariable String id){
		try {
			Course course = courseService.getCourse(id);
			if(course != null)
				return Message.getSuccessMessage(course, HttpStatus.OK);
			else
				return Message.getRuntimeErrorMessage("Invaild", HttpStatus.NOT_FOUND);
		}
		catch (Exception e) { 
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCourse(@PathVariable String id){
		try {
			
			if(courseService.deleteCourse(id) != null)
				return Message.getSuccessMessage("Deleted Successfully", HttpStatus.OK);
			else
				return Message.getRuntimeErrorMessage("Invaild", HttpStatus.NOT_FOUND);
		}
		catch (Exception e) { 
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
