package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.app.dto.feedback.FullRatingDTO;
import com.app.dto.feedback.RatingCountDTO;
import com.app.dto.feedback.StudentFeedbackSubmittedDTO;
import com.app.pojos.Feedback;
import com.app.pojos.FeedbackCriteria;
import com.app.pojos.StudentFeedbackRatings;

public interface FeedbackService {
	Feedback addNewFeedback(Feedback feedback);
	FullRatingDTO addFullRating(FullRatingDTO fullRating);
	Feedback getFeedbackById(Long id);
	StudentFeedbackSubmittedDTO checkFeedbackSubmitted(Long moduleId, Long studentId);
	List<Feedback> getFeedbacksByModuleId(Long moduleId);
//	float getAverageRankByModuleId(Long moduleId);
	List<StudentFeedbackRatings> insertRatings(ArrayList<StudentFeedbackRatings> allRatings); 
	List<Feedback> getAllFeedbackByModuleId(Long moduleId);
	List<Feedback> getAllFeedbackByModuleIdBatchId(Long moduleId, String batchId);
//	StudentFeedbackRatings insertRating(StudentFeedbackRatings rating);
	List<RatingCountDTO> countRatingsByFeedbackIds(List<Long> feedbackIds);
	List<FeedbackCriteria> getAllCriteria();
}
