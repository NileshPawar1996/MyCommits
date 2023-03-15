package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.FeedbackCriteriaDao;
import com.app.dao.FeedbackDao;
import com.app.dao.StudentFeedbackRatingsDao;
import com.app.dto.feedback.FullRatingDTO;
import com.app.dto.feedback.RatingCountDTO;
import com.app.dto.feedback.StudentFeedbackSubmittedDTO;
import com.app.pojos.Feedback;
import com.app.pojos.FeedbackCriteria;
import com.app.pojos.StudentFeedbackRatings;

@Service
public class FeedbackServiceImpl implements FeedbackService{

	@Autowired
	FeedbackDao feedbackDao;
	
	@Autowired
	StudentFeedbackRatingsDao ratingsDao;
	
	@Autowired
	FeedbackCriteriaDao criteriaDao;
	
	@Override
	public Feedback addNewFeedback(Feedback feedback) {
		// TODO Auto-generated method stub
		Feedback persistedFeedback = feedbackDao.save(feedback);
		return persistedFeedback;
	}

	@Override
	public Feedback getFeedbackById(Long id) {
		// TODO Auto-generated method stub
		System.out.println("in service");
		Optional<Feedback> persistedFeedback = feedbackDao.findById(id);
		Feedback fb = persistedFeedback.orElse(null);
//		System.out.println(persistedFeedback.getComment());
		return fb;
	}

	@Override
	public List<Feedback> getFeedbacksByModuleId(Long moduleId) {
		// TODO Auto-generated method stub
		return null;
	}

//	public float getAverageRankByModuleId(Long moduleId) {
//		List<Integer> allRatings = ratingsDao.findByModuleId(moduleId);
//		float sum = 0, size = allRatings.size();
//		for(int a :allRatings) {
//			sum = sum + a;
//		}
//		float avgRating = sum/size;
//		return avgRating;
//	}

	@Override
	public List<StudentFeedbackRatings> insertRatings(ArrayList<StudentFeedbackRatings> allRatings) {
//		allRatings.trimToSize();
		List<StudentFeedbackRatings> persistedRatings = ratingsDao.saveAll(allRatings);
		
		return persistedRatings;
	}

	//adityaN
	@Override
	public List<Feedback> getAllFeedbackByModuleId(Long moduleId) {
	
		return feedbackDao.findByModuleId(moduleId);
	}
	
	

		@Override
		public StudentFeedbackSubmittedDTO checkFeedbackSubmitted(Long moduleId, Long studentId) {
			Feedback studentFeedback = feedbackDao.findByModuleIdAndStudentId(moduleId, studentId).orElse(null);
			
			return new StudentFeedbackSubmittedDTO(studentFeedback != null);
		}

		@Override
		public FullRatingDTO addFullRating(FullRatingDTO fullRating) {
			Feedback persistedFeedback = feedbackDao.save(fullRating.getStudentFeedback());
			
			List<StudentFeedbackRatings> criteriaRating = fullRating.getCriteriaRating();
			
			for (StudentFeedbackRatings rating : criteriaRating) {
				rating.setFeedback(persistedFeedback);
			}
			
			List<StudentFeedbackRatings> persistedRatings = ratingsDao.saveAll(criteriaRating);
			return new FullRatingDTO(persistedFeedback, persistedRatings);
		}

		@Override
		public List<FeedbackCriteria> getAllCriteria() {
			return  criteriaDao.findAll();
		}

		@Override
		public List<Feedback> getAllFeedbackByModuleIdBatchId(Long moduleId, String batchId) {
			return feedbackDao.findByModuleIdAndBatchId(moduleId, batchId);
		}

		@Override
		public List<RatingCountDTO> countRatingsByFeedbackIds(List<Long> feedbackIds) {
			return ratingsDao.countRatingsByFeedbackIds(feedbackIds);
		}

	
	
}
