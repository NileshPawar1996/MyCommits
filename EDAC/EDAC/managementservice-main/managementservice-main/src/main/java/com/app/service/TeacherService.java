package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.daoInterface.AdminDaoInterface;
import com.app.daoInterface.TeacherDaoInterface;
import com.app.dtos.admin.GetAdminDetails;
import com.app.dtos.teacher.GetCourseBatchModuleByTeacherId;
import com.app.dtos.teacher.GetTeacherDetails;
import com.app.pojos.Course;
import com.app.pojos.Module;
import com.app.pojos.Role;
import com.app.pojos.Staff;

@Service
public class TeacherService {
	@Autowired
	TeacherDaoInterface teacherDao;
	
	public String addTeacher(Staff admin) {
		teacherDao.save(admin);
		return "success";
	}

	public List<GetTeacherDetails> getAllTeachers() {
		List<Staff> allTeachers = teacherDao.findAllTeachers();
		List<GetTeacherDetails> returnList = new ArrayList<>();
		for (Staff teacher : allTeachers) {
			returnList.add(new GetTeacherDetails(teacher.getId(), 
					teacher.getFirstName(), teacher.getLastName(), 
					teacher.getEmail(), teacher.getContactDetails(), 
					teacher.getRole(), teacher.getSalary(), teacher.getExperience(),
					teacher.getJoinDate()));
		}
		return returnList;
	}
	
	public Staff findByTeacherId(Long id) {
		return teacherDao.findByTeacherId(id);
	}
	
	public String editTeacher(Long id, Staff teacher) {
		teacher.setId(id);
		if(teacherDao.existsById(teacher.getId())) {
			teacherDao.save(teacher);
			return "success";
		}
		else
			return "failed";	
	}
	
	public String deleteTeacher(Long id) {
		if(teacherDao.existsById(id)) {
			Staff teacherDetails = findByTeacherId(id);
			if(teacherDetails != null) {
				teacherDao.deleteById(id);
				return "success";
			}
		}
		return "failed";
	}
	
//	public GetCourseBatchModuleByTeacherId getCourseBatchModuleOfTeacher(Long id) {
//		List<String> courseList = teacherDao.findDistinctCoursesOfTeacher(id);
//		List<Long> batchList = teacherDao.findDistinctBatchesOfTeacher(id);
//		
//		List<Long> moduleList = teacherDao.findDistinctModulesOfTeacher(id);
//		GetCourseBatchModuleByTeacherId returnObj = null;
//		if(courseList != null && batchList != null && moduleList != null) {
//			returnObj = new GetCourseBatchModuleByTeacherId();
//			returnObj.setCourseId(courseList);
//			returnObj.setBatchId(batchList);
//			returnObj.setModuleId(moduleList);
//		}
//		return returnObj;
//	}

	public List<Long> getBatchesOfTeacher(Long id) {
		List<Long> batchList = teacherDao.findDistinctBatchesOfTeacher(id);
		return batchList;
	}
	
	public List<String> getCoursesOfTeacher(Long id) {
		List<String> courseList = teacherDao.findDistinctCoursesOfTeacher(id);
		return courseList;
	}
	
	public List<Long> getModulesOfTeacher(Long batchId, Long teacherId) {
		List<Long> moduleList = teacherDao.findDistinctModulesOfTeacher(batchId, teacherId);
		return moduleList;
	}
	
}
