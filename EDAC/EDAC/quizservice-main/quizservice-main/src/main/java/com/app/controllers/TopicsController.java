//CREATED BY RAJVARDHAN SHINDE
package com.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Topic;
import com.app.service.topicsService.TopicsService;
import com.app.utils.CreateResult;

@RestController
@RequestMapping("/topic")
public class TopicsController {

	@Autowired
	private TopicsService topicsService;

//----------------------------- ADD A TOPIC ------------------------------------------------------	
	@PostMapping("/add")
	public ResponseEntity<?> addTopic(@RequestBody Topic topic) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			topicsService.addTopic(topic);
			return CreateResult.createSuccessResult("Topic added Successfully", HttpStatus.CREATED);
		} catch (Exception ex) {
			return CreateResult.createErrorResult(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//-----------------------------FIND TOPICS BY MODULE ------------------------------------------------------
	@GetMapping("/module/{moduleId}")
	public ResponseEntity<?> getQuestionByTopic(@PathVariable Long moduleId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Topic> topics = topicsService.getAllTopicsByModule(moduleId);
			return CreateResult.createSuccessResult(topics, HttpStatus.OK);
		} catch (Exception ex) {
			return CreateResult.createErrorResult(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//----------------------------- EDIT A TOPIC ------------------------------------------------------	
	@PutMapping("/edit")
	public ResponseEntity<?> editTopic(@RequestBody Topic topic) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			topicsService.editTopic(topic);
			return CreateResult.createSuccessResult("Topic edited Successfully", HttpStatus.ACCEPTED);
		} catch (Exception ex) {
			return CreateResult.createErrorResult(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//----------------------------- DELETE A TOPIC ------------------------------------------------------	
	@DeleteMapping("/delete/{topicId}")
		public ResponseEntity<?> deleteTopic(@PathVariable ("topicId") Long topicId) {
			try {
				topicsService.deleteTopic(topicId);
				return CreateResult.createSuccessResult("Topic deleted successfully", HttpStatus.ACCEPTED);
			} catch (Exception ex) {
				return CreateResult.createErrorResult(ex, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
}
