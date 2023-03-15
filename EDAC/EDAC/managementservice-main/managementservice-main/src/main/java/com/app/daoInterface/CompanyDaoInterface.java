package com.app.daoInterface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.pojos.Batch;
import com.app.pojos.Company;
import com.app.pojos.Course;
import com.app.pojos.Staff;

@Repository
public interface CompanyDaoInterface extends JpaRepository<Company, Long>{
	
}
