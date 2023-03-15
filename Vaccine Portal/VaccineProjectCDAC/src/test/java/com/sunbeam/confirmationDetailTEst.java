package com.sunbeam;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sunbeam.daos.BookSlotDao;
import com.sunbeam.daos.UserDao;
import com.sunbeam.daos.VaccinationDetailDao;
import com.sunbeam.daos.confirmationdao;
import com.sunbeam.entities.BookSlot;
import com.sunbeam.entities.ConfirmationDetail;
import com.sunbeam.entities.User;
import com.sunbeam.entities.VaccinationDetail;

@SpringBootTest
class confirmationDetailTEst {
	
	
	@Autowired
	private confirmationdao dao;

	@Test
	void TestFindAll() {
		
		List<ConfirmationDetail> list=dao.findAll();
		list.forEach(System.out::println);
	}

}
