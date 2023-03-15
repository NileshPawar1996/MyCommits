package com.sunbeam.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeam.entities.User;
import com.sunbeam.entities.VaccinationDetail;

public interface VaccinationDetailDao extends JpaRepository<VaccinationDetail, Integer> {

	 //  VaccinationDetail findById(int id);
	
}
