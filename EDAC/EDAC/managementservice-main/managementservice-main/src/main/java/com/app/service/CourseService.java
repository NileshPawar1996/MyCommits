package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.daoInterface.AdminDaoInterface;
import com.app.daoInterface.CourseDaoInterface;
import com.app.daoInterface.SessionDaoInterface;
import com.app.dtos.module.StatusAndTeacherNameDto;
import com.app.pojos.Course;
import com.app.pojos.Module;
import com.app.pojos.Staff;

@Service
public class CourseService {
	@Autowired
	CourseDaoInterface courseDao;
	@Autowired
	ModuleService moduleService;
	
	
	public String addCourse(Course course) {
		courseDao.save(course);
		return "success";
	}

	public List<Course> getAllCourses() {
		return courseDao.findAll();
	}

	public List<StatusAndTeacherNameDto> getStatusOfAllModuleCompletion(String id, Long batchId) {
		List<Module> allModules = moduleService.getModuleByCourse(id);
		List<StatusAndTeacherNameDto> listToReturn = new ArrayList<>();
		for (Module module : allModules) {
			StatusAndTeacherNameDto moduleStatusDto = moduleService.isModuleCompleted(module, batchId);
			if(moduleStatusDto != null)
				listToReturn.add(moduleStatusDto);
		}

		return listToReturn;
	}

	public Course getCourse(String id) {
		return courseDao.findByCourseId(id);
	}

	public String deleteCourse(String id) {
		if(courseDao.findByCourseId(id) != null) {
			courseDao.deleteByIdCustom(id);
			return "Deleted";
		}
		else
			return null;
	}
}
