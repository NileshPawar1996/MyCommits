package com.app.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.function.EntityResponse;

import com.app.dto.feedback.FullRatingDTO;
import com.app.dto.feedback.RatingCountDTO;
import com.app.dto.feedback.StudentFeedbackSubmittedDTO;
import com.app.pojos.Feedback;
import com.app.pojos.FeedbackCriteria;
import com.app.pojos.StudentFeedbackRatings;
import com.app.service.FeedbackService;
import com.app.utils.Message;

@CrossOrigin
@RestController
@RequestMapping("/feedback")
public class FeedbackController {
	@Autowired
	FeedbackService fbService;
	
	
	public FeedbackController(){
		System.out.println("HomeController constructor called");
	}
	
	@GetMapping("/scheduled/{batchId}")
	public ResponseEntity<Object> getSchduledFeedbacks(@PathVariable Long batchId){
		try {
			RestTemplate apiCaller = new RestTemplate();
			Object result = apiCaller.getForObject("http://192.168.81.209:8080/manage/session/batch/1", Object.class);
			return Message.getSuccessMessage(result, HttpStatus.OK);
		}catch(Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("submitted/{studentId}/{moduleId}")
	public ResponseEntity<Object> getIfSubmitted(@PathVariable Long studentId, @PathVariable Long moduleId){
		try {	
			StudentFeedbackSubmittedDTO isSubmitted = fbService.checkFeedbackSubmitted(moduleId,studentId);		
			return Message.getSuccessMessage(isSubmitted, HttpStatus.OK);
		}catch(Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
//	@GetMapping
//	public String welcome(){
//		return "welcome";
//	}
	
	@PostMapping("/add")
	public ResponseEntity<Object> addNewFeedback(@RequestBody Feedback feedback){
		
		try {
			fbService.addNewFeedback(feedback);
			return Message.getSuccessMessage("Feedback added successfully", HttpStatus.OK);
		}catch(Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


//	@PostMapping("/addRatings")
//	public ResponseEntity<Object> addRatings(@RequestBody ArrayList<StudentFeedbackRatings> feedbackData){
//		try {			
//			 fbService.insertRatings(feedbackData);
//			return  Message.getSuccessMessage("Ratings Added successfully", HttpStatus.OK);
//		}catch(Exception e) {
//			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

	
	@PostMapping("/addfull")
	public ResponseEntity<Object> addRatings(@RequestBody FullRatingDTO fullRating){
		try {			
			 FullRatingDTO addedFullRating = fbService.addFullRating(fullRating);
			return  Message.getSuccessMessage(addedFullRating, HttpStatus.OK);
		}catch(Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
//	@PostMapping("/addRatings")
//	public ResponseEntity<Object> addRatings(@RequestBody List<Object> feedbackData){
//		try {
//			
//			Feedback feedback = (Feedback)feedbackData.get(0);				//first object in the list would be the feedback
//			Feedback savedFB = fbService.addNewFeedback(feedback);			//persisting  the feedback and catching the persisted feedback object
//			List<StudentFeedbackRatings> ratingsToSave = new ArrayList<>(); //creating a new list to save all the ratings
//			for(int i =1;i<5;i ++) {
//				//iterating over the list and setting the feedback id in ech of the rating object
//				
//				StudentFeedbackRatings tempRating;
//				tempRating = (StudentFeedbackRatings)feedbackData.get(i);
//				tempRating.setFeedback(savedFB);
//				ratingsToSave.set(i-1,tempRating);
//			}
//				
//			fbService.insertRatings(ratingsToSave);
//			return  Message.getSuccessMessage("Ratings Added successfully", HttpStatus.OK);
//		}catch(Exception e) {
//			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	
	
//	
//	@PostMapping("/addRating")
//	public ResponseEntity<Object> addRatings(@RequestBody StudentFeedbackRatings rating){
//		try {
//			fbService.insertRating(rating);
//			return  Message.getSuccessMessage("Ratings Added successfully", HttpStatus.OK);
//		}catch(Exception e) {
//			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	@GetMapping("/{FeedbackId}")
	public ResponseEntity<Object> getFeedbackById(@PathVariable Long FeedbackId){
		
		try {
			Feedback found = fbService.getFeedbackById(FeedbackId);
			return Message.getSuccessMessage(found, HttpStatus.OK);
		}catch(Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@GetMapping("/criteria/all")
	public ResponseEntity<Object> getAllCriteria(){
		try {
			List<FeedbackCriteria> allCriteria = fbService.getAllCriteria();
			return Message.getSuccessMessage(allCriteria, HttpStatus.OK);
		}catch(Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	//adityaN
		@GetMapping("/module/{moduleId}")
		public ResponseEntity<Object> getAllFeedbackByModuleId(@PathVariable Long moduleId){
			System.out.println("run krke dekh rha");
			//System.out.println(moduleId);
			List<Feedback> feedback = fbService.getAllFeedbackByModuleId(moduleId);
			return Message.getSuccessMessage(feedback, HttpStatus.OK);
		}

		//sahilm
		@GetMapping("/module/{moduleId}/{batchId}")
		public ResponseEntity<Object> getAllFeedbackByModuleIdBatchId(@PathVariable Long moduleId,@PathVariable String batchId){
			//System.out.println(moduleId);
			List<Feedback> feedbacks = fbService.getAllFeedbackByModuleIdBatchId(moduleId, batchId);
			return Message.getSuccessMessage(feedbacks, HttpStatus.OK);
		}
		
		@GetMapping("/ratings")
		public ResponseEntity<Object> getAllRatingsBy(@RequestParam List<Long> ids){
			List<RatingCountDTO> ratings = fbService.countRatingsByFeedbackIds(ids);
			return Message.getSuccessMessage(ratings, HttpStatus.OK);
		}
	
//	@GetMapping("/rating/{moduleId}")
//	public float getFeedbackByModuleId(@PathVariable Long moduleId){
//		float avgRating = fbService.getAverageRankByModuleId(moduleId);
//		return avgRating;
//	}
}
