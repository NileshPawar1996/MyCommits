package com.sunbeam.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sunbeam.entities.User;

@SpringBootTest
class UserServiceImplTests {
	@Autowired
	private UserServiceImpl userService;
	
	@Test
	void testFindUserByEmail() {
		User user = userService.findUserByEmail("admin@gaana.com");
		assertNotNull(user);
	}

	@Test
	void testFindUserByEmailAndPassword() {
		User user = userService.findUserByEmailAndPassword("nilesh@gmail.com", "nilesh");
		assertNotNull(user);
		user = userService.findUserByEmailAndPassword("nilesh@gmail.com", "nilesh123");
		assertNull(user);
	}

	@Test
	void testSaveUser() {
		User user = new User(0, "Smita", "smita@gmail.com", "9422012345", "Pune", "smita", null);
		userService.saveUser(user);
		assertNotEquals(0, user.getId());
	}

}
