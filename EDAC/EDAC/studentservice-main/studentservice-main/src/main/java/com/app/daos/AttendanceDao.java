package com.app.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.pojos.Attendance;

public interface AttendanceDao extends JpaRepository<Attendance, Long>{
	
	public List<Attendance> findByStudentId(Long studentId);
	@Query("select a FROM Attendance a WHERE fk_student_id = ?1 AND fk_session_id = ?2")
	public List<Attendance> findByStudentIdAndSessionId(Long studentId, Long sessionId);
	@Query(value ="select * FROM attendance WHERE fk_student_id = :studentId AND fk_session_id IN (:sessionString)", nativeQuery = true )
	public List<Attendance> findByStudentIdAndMultipleSessionId(@Param("studentId") Long studentId,@Param("sessionString") StringBuilder sessionString);
	
	
	
}
