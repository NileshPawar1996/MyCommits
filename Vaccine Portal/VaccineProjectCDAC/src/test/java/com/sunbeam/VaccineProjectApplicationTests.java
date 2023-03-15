package com.sunbeam;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sunbeam.daos.UserDao;
import com.sunbeam.entities.User;

@SpringBootTest
class VaccineProjectApplicationTests {
	
	
	@Autowired
	private UserDao userdao;

	@Test
	void TestFindAll() {
		
		List<User> list=userdao.findAll();
		list.forEach(System.out::println);
	}

}
