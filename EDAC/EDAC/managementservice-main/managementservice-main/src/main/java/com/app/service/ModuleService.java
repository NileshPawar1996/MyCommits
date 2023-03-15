package com.app.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.daoInterface.ModuleDaoInterface;
import com.app.dtos.module.StatusAndTeacherNameDto;
import com.app.pojos.Course;
import com.app.pojos.Module;
import com.app.pojos.Session;

@Service
public class ModuleService {
	@Autowired
	ModuleDaoInterface moduleDao;
	
	@Lazy
	@Autowired
	SessionService sessionService;
	
	public String addModule(Module module) {
		moduleDao.save(module);
		return "success";
	}

	public List<Module> getAllModules() {
		return moduleDao.findAll();
	}
	
	public String editModule(Module module) {
		if(moduleDao.existsById(module.getId())) {
			moduleDao.save(module);
			return "success";
		}
		else
			return "failed";	
	}

	public Module getModule(Long id) {
		Module module = moduleDao.findById(id).orElse(null);
		return module;
	}
	
	public List<Module> getModuleByCourse(String course) {
		List<Module> module = moduleDao.findByCourse(course);
		return module;
	}
	
	public StatusAndTeacherNameDto isModuleCompleted(Module module, Long batchId) {
		List<Session> lisOfSessions = sessionService.getSessionByModuleIdAndCourseId(module.getId(), batchId);
		if(lisOfSessions.size() == 0) {
			return null;
		}
		StatusAndTeacherNameDto statusAndTeacher = new StatusAndTeacherNameDto();
//		Module module = getModule(id);
		int durationOfModule = module.getDurationHrs();
		Boolean flag = false;
		for (Session session : lisOfSessions) {
			if(flag == false) {
				String teacherName = session.getStaff().getFirstName()+session.getStaff().getLastName();
				statusAndTeacher.setTeacherName(teacherName);
				flag = true;
			}
			if(session.getEndTime() != null && session.getStartTime() != null) {
				long endHour = session.getEndTime().getHour();
				long startHour = session.getStartTime().getHour();
				durationOfModule -= (endHour - startHour);
			}
		}
		statusAndTeacher.setIsCompleted(true?durationOfModule<=0:false);
		statusAndTeacher.setModuleId(module.getId());
		statusAndTeacher.setModuleName(module.getModuleName());
		return statusAndTeacher;
	}
	
	public String deleteModule(Long id) {
		if(moduleDao.existsById(id)) {
			moduleDao.deleteById(id);
			return "success";
		}
		else
			return "failed";	
	}
	
}
