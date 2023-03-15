package com.app.Service;

import java.util.List;

import com.app.pojos.Attendance;

public interface AttendanceService {
	List<Attendance> getAttendanceByStudentId(Long studentId);
	List<Attendance> getByStudentIdAndSessionId(Long studentId, Long sessionId);
	List<Attendance> saveAllAttendance(List<Attendance> aList);
	List<Attendance> findByStudentIdAndMultipleSessionId(Long studentId, StringBuilder sessionString);
}
