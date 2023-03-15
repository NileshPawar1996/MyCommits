package com.app.daoInterface;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.pojos.Course;
import com.app.pojos.Staff;

@Repository
@Transactional
public interface CourseDaoInterface extends JpaRepository<Course, Long>{

	@Query(value="SELECT * FROM courses WHERE course_id = ?1", nativeQuery = true)
	Course findByCourseId(String id);
	
	@Modifying
	@Query(value="DELETE FROM courses WHERE course_id = ?1", nativeQuery = true)
	void deleteByIdCustom(String id);
	
}
