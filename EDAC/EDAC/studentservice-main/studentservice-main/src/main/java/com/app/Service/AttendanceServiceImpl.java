package com.app.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.daos.AttendanceDao;
import com.app.pojos.Attendance;
@Service
public class AttendanceServiceImpl implements AttendanceService{
	
	@Autowired
	AttendanceDao attendanceDao;
	@Override
	public List<Attendance> getAttendanceByStudentId(Long studentId) {
		List<Attendance> foundList= attendanceDao.findByStudentId(studentId);
		return foundList;
	}
	
	@Override
	public List<Attendance> getByStudentIdAndSessionId(Long studentId, Long sessionId) {
		List<Attendance> foundList= attendanceDao.findByStudentIdAndSessionId(studentId, sessionId);
		return foundList;
	}

	@Override
	public List<Attendance> saveAllAttendance(List<Attendance> aList) {
		List<Attendance> savedList = attendanceDao.saveAll(aList);
		return savedList;
	}
	
	public List<Attendance> getAllByStudentIdSessionId(Long studentId, List<Long> sessionIds){
		return null;
	}

	@Override
	public List<Attendance> findByStudentIdAndMultipleSessionId(Long studentId, StringBuilder sessionString) {
		return attendanceDao.findByStudentIdAndMultipleSessionId(studentId,sessionString);
//		return null;
	};
	
}
