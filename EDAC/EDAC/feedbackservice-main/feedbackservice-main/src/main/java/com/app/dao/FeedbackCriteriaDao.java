package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.FeedbackCriteria;

public interface FeedbackCriteriaDao extends JpaRepository<FeedbackCriteria, Long> {

}
