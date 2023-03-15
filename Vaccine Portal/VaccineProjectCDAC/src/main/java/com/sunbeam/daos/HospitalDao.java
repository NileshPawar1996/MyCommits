package com.sunbeam.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeam.entities.HospitalDetail;

public interface HospitalDao extends JpaRepository<HospitalDetail, Integer> {

}
