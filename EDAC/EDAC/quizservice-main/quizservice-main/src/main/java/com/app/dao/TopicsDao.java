//CREATED BY RAJVARDHAN SHINDE
package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Topic;

public interface TopicsDao extends JpaRepository<Topic, Long>{
	
	List<Topic> findByModuleId(Long moduleId);
	
}
