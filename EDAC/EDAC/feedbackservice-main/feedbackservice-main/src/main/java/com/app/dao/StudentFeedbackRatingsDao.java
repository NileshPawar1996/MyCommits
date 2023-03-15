package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.dto.feedback.RatingCountDTO;
import com.app.pojos.StudentFeedbackRatings;

public interface StudentFeedbackRatingsDao extends JpaRepository<StudentFeedbackRatings, Long> {

	public List<Integer> findAllById(Long moduleId);
	
//	@Query(value = "select feedback, criteria, count(rating) as count, rating from student_feedback_ratings where feedback in :feedbackIds group by rating, criteria order by criteria, rating desc", nativeQuery = true)
	@Query("select new com.app.dto.feedback.RatingCountDTO( r.feedback, r.criteria, count(r.rating), r.rating)"
		       +" from StudentFeedbackRatings r"
		       +" WHERE r.feedback.id IN ?1"
		       +" group by r.rating, r.criteria"
		       + " order by r.criteria, r.rating")
	public List<RatingCountDTO> countRatingsByFeedbackIds(List<Long> feedbackIds);

//	public List<Integer> findByModuleId(Long moduleId);

//	public List<StudentFeedbackRatings> saveAll(List<StudentFeedbackRatings> allRatings);
}
