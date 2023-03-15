package com.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.pojos.Feedback;

public interface FeedbackDao extends JpaRepository<Feedback, Long>{
	public Optional<Feedback> findById(Long id);
	
	List<Feedback> findByModuleId(Long moduleId); //adityaN
	List<Feedback> findByModuleIdAndBatchId(Long moduleId, String batchId); //sahilm
	Optional<Feedback> findByModuleIdAndStudentId(Long moduleId, Long studentId); //sahilm
}