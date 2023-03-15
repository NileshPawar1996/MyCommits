package com.sunbeam;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sunbeam.daos.UserDao;
import com.sunbeam.daos.VaccinationDetailDao;
import com.sunbeam.entities.User;
import com.sunbeam.entities.VaccinationDetail;

@SpringBootTest
class VaccineDetailTestClass {
	
	
	@Autowired
	private VaccinationDetailDao vacdao;

	@Test
	void TestFindAll() {
		
		List<VaccinationDetail> list=vacdao.findAll();
		list.forEach(System.out::println);
	}

}
