package com.app.daoInterface;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.app.pojos.Staff;

@Repository
public interface TeacherDaoInterface extends JpaRepository<Staff, Long>{
	@Query(value = "SELECT * FROM staff WHERE role = 'TEACHER'", nativeQuery = true)
	List<Staff> findAllTeachers();
	
	@Query(value = "SELECT * FROM staff WHERE role = 'TEACHER' AND staff_id = ?1", nativeQuery = true)
	Staff findByTeacherId(Long id);
	
	@Query(value = "SELECT distinct(course_id) FROM courses c INNER JOIN batches b ON(c.course_id = b.fk_course_id)  INNER JOIN sessions s ON (b.batch_surrogate_id = s.fk_batch_id) where s.fk_staff_id = ?1", nativeQuery = true)
	List<String> findDistinctCoursesOfTeacher(Long id);
	
	@Query(value = "SELECT distinct(b.batch_surrogate_id) FROM courses c INNER JOIN batches b ON(c.course_id = b.fk_course_id)  INNER JOIN sessions s ON (b.batch_surrogate_id = s.fk_batch_id) where s.fk_staff_id = ?1", nativeQuery = true)
	List<Long> findDistinctBatchesOfTeacher(Long id);
	
	@Query(value = "SELECT distinct(s.fk_module_id) FROM courses c INNER JOIN batches b ON(c.course_id = b.fk_course_id)  INNER JOIN sessions s ON (b.batch_surrogate_id = s.fk_batch_id) where b.batch_surrogate_id = ?1 AND s.fk_staff_id = ?2", nativeQuery = true)
	List<Long> findDistinctModulesOfTeacher(Long batchId, Long teacherId);	
	
}