package com.sunbeam.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sunbeam.entities.User;

public interface UserDao  extends JpaRepository<User, Integer>{
	
	
	User findById(int id);
	


	User findByMobileNumber(String mobileNumber);
	
	@Query(value="insert into User values(1,'Nanded','Gorakhanath','Lingayat','9689028684','123','ADMIN') ",nativeQuery=true)
    User InsertAdmin();
}
