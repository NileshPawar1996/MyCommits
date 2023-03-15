//CREATED BY RAJVARDHAN SHINDE
package com.app.controllers;

import java.util.ArrayList;
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

import com.app.dto.QuestionDto;
import com.app.pojos.Exam;
import com.app.pojos.ExamQuestion;
import com.app.pojos.Question;
import com.app.service.examQuestionService.ExamQuestionServiceImplementation;
import com.app.service.examsService.ExamsService;
import com.app.utils.CreateResult;

@RestController
@RequestMapping("/examQuestion")
public class ExamQuestionsController {

	@Autowired
	private ExamQuestionServiceImplementation examQuestionsService;
	@Autowired
	private ExamsService examService;

//----------------------------- ADD AN EXAM_QUESTION ------------------------------------------------------	
	@PostMapping("/add")
	public ResponseEntity<?> addExamQuestion(@RequestBody ExamQuestion exam) {
		try {
			examQuestionsService.addExamQuestion(exam);
			return CreateResult.createSuccessResult("Exam_Question Added Successfully", HttpStatus.CREATED);
		} catch (Exception ex) {
			return CreateResult.createErrorResult(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//----------------------------- DELETE AN EXAM_QUESTION BY EXAM ID AND QUESTION ID ------------------------------------------------------	
	@DeleteMapping("/delete/exam/{examId}/question/{questionId}")
	public ResponseEntity<?> deleteExamQuestion(@PathVariable("examId") Long examId,
			@PathVariable("questionId") Long queId) {
		try {
			examQuestionsService.deleteExamQuestionByExamIdAndModuleId(examId, queId);
			return CreateResult.createSuccessResult("Exam_Question Deleted Successfully", HttpStatus.ACCEPTED);
		} catch (Exception ex) {
			return CreateResult.createErrorResult(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//----------------------------- GET ALL QUESTIONS BY EXAM_ID ------------------------------------------------------	
	@GetMapping("/getQuestionsByExamIdDTO/{examId}")
	public ResponseEntity<?> getQuestionsByExamIdDTO(@PathVariable("examId") Long examId) {
		try {
			ArrayList<QuestionDto> ques = new ArrayList<QuestionDto>();
			ArrayList<ExamQuestion> examQuestionsList = (ArrayList<ExamQuestion>) examQuestionsService
					.getExamQuestionByExamId(examId);
			examQuestionsList.forEach((e) -> {
				Question q = e.getQuestion();
				QuestionDto qdto = new QuestionDto(q.getId(), q.getQuestion(), q.getOptionA(), q.getOptionB(),
						q.getOptionC(), q.getOptionD());
				ques.add(qdto);
			});
			return CreateResult.createSuccessResult(ques, HttpStatus.ACCEPTED);
		} catch (Exception ex) {
			return CreateResult.createErrorResult(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//----------------------------- GET ALL QUESTIONS BY EXAM_ID ------------------------------------------------------	
	@GetMapping("/getQuestionsByExamId/{examId}")
	public ResponseEntity<?> getQuestionsByExamId(@PathVariable("examId") Long examId) {
		try {
			List<ExamQuestion> ques = examQuestionsService.getExamQuestionByExamId(examId);
			return CreateResult.createSuccessResult(ques, HttpStatus.ACCEPTED);
		} catch (Exception ex) {
			return CreateResult.createErrorResult(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
