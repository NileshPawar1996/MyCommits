package com.sunbeam.controllers;

import javax.validation.Valid;

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

import com.sunbeam.pojos.User;
import com.sunbeam.services.UserService;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/register")
	public ResponseEntity<?> registerNewUser(@RequestBody @Valid User user) {
		return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<?> fetchUserById(@PathVariable Long id) {
		return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
	}

	@GetMapping("/findbyemail")
	public ResponseEntity<?> fetcUserByEmail(@RequestBody String email) {
		return new ResponseEntity<>(userService.findByEmail(email), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
		return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
	}

}