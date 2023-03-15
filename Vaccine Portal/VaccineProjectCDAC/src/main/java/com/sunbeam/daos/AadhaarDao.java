package com.sunbeam.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeam.entities.AdharDetail;
import com.sunbeam.entities.User;

public interface AadhaarDao extends JpaRepository<AdharDetail, Integer> {
	
	
	User findById(int id);

}
