package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.daoInterface.AdminDaoInterface;
import com.app.daoInterface.ModuleDaoInterface;
import com.app.dtos.admin.GetAdminDetails;
import com.app.dtos.teacher.GetTeacherDetails;
import com.app.pojos.Course;
import com.app.pojos.Role;
import com.app.pojos.Staff;

@Service
public class AdminService {
	@Autowired
	AdminDaoInterface adminDao;
	
	public String addAdmin(Staff admin) {
		adminDao.save(admin);
		return "success";
	}

	public String editAdmin(Long id, Staff admin) {
		admin.setId(id);
		if(adminDao.existsById(admin.getId())) {
			adminDao.save(admin);
			return "success";
		}
		else
			return "failed";
	}
	
	public GetAdminDetails getAdminById(Long id) {
		Staff admin = adminDao.findAdminById(id);
		if(admin != null) {
			GetAdminDetails currentAdmin = new GetAdminDetails(admin.getId(), 
					admin.getFirstName(), admin.getLastName(), 
					admin.getEmail(), admin.getContactDetails(), 
					admin.getRole(), admin.getSalary(), admin.getExperience(),
					admin.getJoinDate());
			return currentAdmin;
		}
		return null;
		
	}
	
	public String deleteAdmin(Long id) {
		if(adminDao.existsById(id)) {
			GetAdminDetails adminDetails = getAdminById(id);
			if(adminDetails != null) {
				adminDao.deleteById(id);
				return "success";
			}
		}
		return "failed";
	}
	
	public List<Staff> getAllAdminDetails() {
		return adminDao.getAllAdmins();
	}
}
