package com.sunbeam.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeam.entities.ConfirmationDetail;

public interface confirmationdao extends JpaRepository<ConfirmationDetail, Integer> {
  
	
	ConfirmationDetail findById(int id);
}
