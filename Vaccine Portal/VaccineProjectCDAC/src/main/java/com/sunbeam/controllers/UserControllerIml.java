package com.sunbeam.controllers;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.DTO.Credential;
import com.sunbeam.DTO.Response;
import com.sunbeam.DTO.UserDto;
import com.sunbeam.entities.User;
import com.sunbeam.services.UserserviceImpl;

//cross origin annotation imp for every controller
@CrossOrigin
@RestController
public class UserControllerIml {
    @Autowired
	private UserserviceImpl userservice;
	@PostMapping("/user/signin")
	public  Object signIn(@RequestBody Credential cred) {
		UserDto user = userservice.findUserBymobilenumberAndPassword(cred);
		if(user ==null)
			return Response.error("user not found");
		
		if(user.getRole().equals("ADMIN"))
			return "redirect:AdminController";
			
		if(user.getRole().equals("Citizen"))
			return "redirect:CustomerController";
	return Response.success(user);
	}
	
	
	@PostMapping("/user/signup")
	
	public ResponseEntity<?> Signup(@RequestBody UserDto userDto)
	{
		System.out.println(userDto);
		Map<String,Object> result= userservice.SaveUser(userDto);
		return Response.success(result);
	}
	
}
