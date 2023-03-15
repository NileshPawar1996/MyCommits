package com.app.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.daoInterface.SessionDaoInterface;
import com.app.dtos.session.GetAllSessionsAndAllModulesByModuleIdDto;
import com.app.dtos.session.SessionInDescOrderDto;
import com.app.dtos.session.SessionsForAttendanceCalculationsDto;
import com.app.pojos.Course;
import com.app.pojos.Module;
import com.app.pojos.Session;


@Service
public class SessionService {
	@Autowired
	SessionDaoInterface sessionDao;
	@Autowired
	ModuleService moduleService;
	@Autowired
	SessionService session;
	
	public Session getSession(Long id) {
		Session session = sessionDao.findById(id).orElse(null);
		return session;
	}
	
	public List<?> getSessionAndModule(String courseId, Long batchId) {
		List<Module> allModules = moduleService.getModuleByCourse(courseId);

		System.out.println(allModules);
		List<Object> listToReturn = new ArrayList<Object>();
		for (Module module : allModules) {
			SessionsForAttendanceCalculationsDto outerDtoObj = new SessionsForAttendanceCalculationsDto();
			outerDtoObj.setModuleId(module.getId());
			outerDtoObj.setModuleName(module.getModuleName());
			List<GetAllSessionsAndAllModulesByModuleIdDto> tempList = new ArrayList<GetAllSessionsAndAllModulesByModuleIdDto>();
			List<Session> sessionsByModuleId = session.getSessionByModuleIdAndBatchId(module.getId(), batchId);
			for (Session sessionTemp : sessionsByModuleId) {
				GetAllSessionsAndAllModulesByModuleIdDto innerDtoObj = new GetAllSessionsAndAllModulesByModuleIdDto();
				innerDtoObj.setId(sessionTemp.getId());
				if(sessionTemp.getEndTime() != null && sessionTemp.getStartTime() != null) {
					LocalTime diff= sessionTemp.getEndTime().minusNanos(sessionTemp.getStartTime().toNanoOfDay());
					long minutes = (diff.getHour() * 60) + diff.getMinute();
					innerDtoObj.setSessionDuration(minutes);
					tempList.add(innerDtoObj);
				}
			}
			
			outerDtoObj.setListOfSessions(tempList);
			listToReturn.add(outerDtoObj);
		}
		return listToReturn;
	}
	
	public List<Session> getSessionByModuleIdAndBatchId(Long moduleId, Long batchId) {
		return sessionDao.getSessionByModuleIdAndBatchId(moduleId, batchId);
	}

	public String addSession(Session session) {
		sessionDao.save(session);
		return "success";
	}

	public List<Session> getAllSessions() {
		return sessionDao.findAll();
	}
	
	public String editSession(Long id, Session session) {
		if(sessionDao.existsById(id)) {
			session.setId(id);
			sessionDao.save(session);
			return "success";	
		}
		else
			return "failed";	
	}
	
	public List<Session> getSessionByModuleId(Long id) {
		Module module = moduleService.getModule(id);
		if(module != null) {
			List<Session> sessionList = sessionDao.findByModule(module);
			return sessionList;
		}
		return null;
	}
	
	public List<Session> getSessionByModuleIdAndCourseId(Long id, Long batchId) {
		Module module = moduleService.getModule(id);
		if(module != null) {
			List<Session> sessionList = sessionDao.findByCourseAndBatch(module.getId(), batchId);
			return sessionList;
		}
		return null;
	}

	public List<Session> getSessionByBatchId(Long batch) {
		return sessionDao.getSessionByBatchId(batch);
	}
	
	public List<SessionInDescOrderDto> getSessionInDescendingOrder(int numberOfSessions){
		List<Session> sessions = sessionDao.getSessionInDescendingOrder(numberOfSessions);
		List<SessionInDescOrderDto> returnList = new ArrayList<>();
		for (Session session : sessions) {
			SessionInDescOrderDto returnSession = new SessionInDescOrderDto();
			returnSession.setId(session.getId());
			returnSession.setBatch(session.getBatch().getBatchId());
			returnSession.setCourseName(session.getBatch().getCourse().getCourseName());
			returnSession.setModule(session.getModule().getModuleName());
			returnSession.setDate(session.getDate());
			returnSession.setStaffName(session.getStaff().getFirstName()+" "+session.getStaff().getLastName());
			returnSession.setStartTime(session.getStartTime());
			returnSession.setEndTime(session.getEndTime());
			returnList.add(returnSession);
		}
		return returnList;
	}
	
	
	public String deleteSession(Long id) {
		if(sessionDao.existsById(id)) {
			sessionDao.deleteById(id);
			return "success";
		}
		else
			return "failed";
	}
}
