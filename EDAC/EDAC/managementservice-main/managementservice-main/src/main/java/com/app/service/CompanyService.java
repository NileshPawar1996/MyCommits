package com.app.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.daoInterface.CompanyDaoInterface;
import com.app.pojos.Company;

@Service
public class CompanyService {
	@Autowired
	CompanyDaoInterface companyDao;
	
	public String addCompany(Company company) {
		companyDao.save(company);
		return "success";
	}

	public List<Company> getAllCompany() {
		return companyDao.findAll();
	}
}
