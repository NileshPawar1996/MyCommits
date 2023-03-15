package com.app.daoInterface;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.app.pojos.Module;

@Repository
public interface ModuleDaoInterface extends JpaRepository<Module, Long>{

	@Query(value = "SELECT * FROM modules WHERE fk_course_id = ?1", nativeQuery = true)
	List<Module> findByCourse(String course);

}
