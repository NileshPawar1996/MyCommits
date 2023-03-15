package com.sunbeam;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sunbeam.daos.AadhaarDao;
import com.sunbeam.daos.UserDao;
import com.sunbeam.entities.AdharDetail;
import com.sunbeam.entities.User;

@SpringBootTest
class AadharTestClass {
	
	
	@Autowired
	private AadhaarDao aadhardao;

	@Test
	void TestFindAll() {
		
		List<AdharDetail> list=aadhardao.findAll();
		list.forEach(System.out::println);
	}

}
