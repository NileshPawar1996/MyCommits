package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.CourseService;
import com.app.service.ModuleService;
import com.app.utils.Message;
import com.app.dtos.module.StatusAndTeacherNameDto;
import com.app.pojos.Course;
import com.app.pojos.Module;

@RestController
@RequestMapping("/module")
public class ModuleController {
	@Autowired
	ModuleService moduleService;
	@Autowired
	CourseService courseService;
	
	@PostMapping("/add")
	public ResponseEntity<Object> addModule(@RequestBody Module module){
		try {
			moduleService.addModule(module);
			return Message.getSuccessMessage("Module Added Successfully", HttpStatus.CREATED);
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/edit")
	public ResponseEntity<Object> editModule(@RequestBody Module module){
		try {
			String response = moduleService.editModule(module);
			if(response == "success") {
				return Message.getSuccessMessage("Module Edited Successfully", HttpStatus.ACCEPTED);
			}
			else {
				return Message.getRuntimeErrorMessage("Module Does Not Exists", HttpStatus.NOT_FOUND);
			}
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<Object> getAllModules(){
		try {
			return Message.getSuccessMessage(moduleService.getAllModules(), HttpStatus.OK);
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getModule(@PathVariable Long id){
		try {
			Module module = moduleService.getModule(id);
			if(module != null)
				return Message.getSuccessMessage(moduleService.getModule(id), HttpStatus.OK);
			else
				return Message.getRuntimeErrorMessage("Invalid Module Id", HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/course/{id}")
	public ResponseEntity<Object> getModuleByCourse(@PathVariable String id){
		try {			
			List<Module> modules = moduleService.getModuleByCourse(id);
			if(modules != null)	
				return Message.getSuccessMessage(modules, HttpStatus.OK);
			else
				return Message.getRuntimeErrorMessage("Invalid Module Id", HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteModule(@PathVariable Long id){
		try {			
			String status = moduleService.deleteModule(id);
			if(status == "success")	
				return Message.getSuccessMessage("Module Deleted Successfully", HttpStatus.OK);
			else
				return Message.getRuntimeErrorMessage("Invalid Module Id", HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
