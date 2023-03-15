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

import com.app.dtos.admin.GetAdminDetails;
import com.app.pojos.Staff;
import com.app.service.AdminService;
import com.app.utils.Message;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Object> getAdmin(@PathVariable Long id){
		try {
			GetAdminDetails admin = adminService.getAdminById(id);
			if(admin != null) {
				return Message.getSuccessMessage(admin, HttpStatus.CREATED);
			}
			else {
				return Message.getRuntimeErrorMessage("Invalid Admin Id", HttpStatus.NOT_FOUND);
			}
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<Object> addAdmin(@RequestBody Staff staff){
		try {
			System.out.println(staff);
			adminService.addAdmin(staff);
			return Message.getSuccessMessage("Admin Created Successfully", HttpStatus.CREATED);
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<Object> EditAdmin(@PathVariable Long id, @RequestBody Staff admin){
		try {
			String functionStatus = adminService.editAdmin(id, admin);
			if(functionStatus.equals("success")) {
				return Message.getSuccessMessage("Admin Updated Successfully", HttpStatus.CREATED);
			}
			else
				return Message.getRuntimeErrorMessage("Please Enter Valid Data", HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> DeleteAdmin(@PathVariable Long id){
		try {
			String functionStatus = adminService.deleteAdmin(id);
			if(functionStatus.equals("success")) {	
				return Message.getSuccessMessage("Admin Deleted Successfully", HttpStatus.CREATED);
			}
			else
				return Message.getRuntimeErrorMessage("Cannot Delete Admin", HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<Object> getAllAdminDetails(){
		try {
			List<Staff> admins = adminService.getAllAdminDetails();
			if(admins != null) {
				return Message.getSuccessMessage(admins, HttpStatus.CREATED);
			}
			else {
				return Message.getRuntimeErrorMessage("Invalid Admin Id", HttpStatus.NOT_FOUND);
			}
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
