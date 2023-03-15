package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.pojos.Company;
import com.app.service.CompanyService;
import com.app.utils.Message;

@RestController
@RequestMapping("/company")
public class ComapnyController {
	@Autowired
	CompanyService companyService;
	
	@PostMapping("/add")
	public ResponseEntity<Object> addCourse(@RequestBody Company company){
		try {
			companyService.addCompany(company);	
			return Message.getSuccessMessage("Company Added Successfully", HttpStatus.CREATED);
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<Object> getAllCompanies(){
		try {
			return Message.getSuccessMessage(companyService.getAllCompany(), HttpStatus.CREATED);
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
