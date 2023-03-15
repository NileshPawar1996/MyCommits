//CREATED BY RAJVARDHAN SHINDE
package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Question;
import com.app.service.questionsService.QuestionsService;
import com.app.utils.CreateResult;

@RestController
@RequestMapping("/question")
public class QuestionsController {

	@Autowired
	private QuestionsService questionsService;

//----------------------------- ADD A QUESTION ------------------------------------------------------	
	@PostMapping("/add")
	public ResponseEntity<?> addQuestion(@RequestBody Question question) {
		try {
			questionsService.addQuestion(question);
			return CreateResult.createSuccessResult("Question added successfully", HttpStatus.CREATED);
		} catch (Exception ex) {
			return CreateResult.createErrorResult(ex, HttpStatus.BAD_REQUEST);
		}

	}

//-----------------------------FIND QUESTIONS BY TOPIC ------------------------------------------------------
	@GetMapping("/topic/{topicId}")
	public ResponseEntity<?> getQuestionByTopic(@PathVariable Long topicId) {
		try {
			List<Question> ques = questionsService.getQuestionsByTopic(topicId);
			return CreateResult.createSuccessResult(ques, HttpStatus.OK);
		} catch (Exception ex) {
			return CreateResult.createErrorResult(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//----------------------------- FIND QUESTIONS BY MODULE ------------------------------------------------------
	@GetMapping("/module/{moduleId}")
	public ResponseEntity<?> getQuestionByModule(@PathVariable Long moduleId) {
		try {
			List<Question> ques = questionsService.getQuestionsByModule(moduleId);
			return CreateResult.createSuccessResult(ques, HttpStatus.OK);
		} catch (Exception ex) {
			return CreateResult.createErrorResult(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//----------------------------- FIND QUESTIONS BY TOPIC AND MODULE ------------------------------------------------------	
	@GetMapping("/topic/{topicId}/module/{moduleId}")
	public ResponseEntity<?> getQuestionByTopicAndModule(@PathVariable("topicId") Long topicId,
			@PathVariable("moduleId") Long moduleId) {
		try {
			List<Question> ques = questionsService.getQuestionsByTopicAndModule(topicId, moduleId);
			return CreateResult.createSuccessResult(ques, HttpStatus.OK);
		} catch (Exception ex) {
			return CreateResult.createErrorResult(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//----------------------------- DELETE A QUESTION BY QUESTION_ID ------------------------------------------------------	
	@DeleteMapping("/delete/{questionId}")
	public ResponseEntity<?> deleteQuestion(@PathVariable("questionId") Long questionId) {
		try {
			questionsService.deleteQuestion(questionId);
			return CreateResult.createSuccessResult("Deleted Question Successfully", HttpStatus.OK);
		} catch (Exception ex) {
			return CreateResult.createErrorResult(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


}
