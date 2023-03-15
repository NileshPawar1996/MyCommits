package com.app.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Exam;
import com.app.pojos.StudentExamAnswer;
import com.app.service.examsService.ExamsService;
import com.app.service.studentExamAnswersService.StudentExamAnswersService;
import com.app.utils.CreateResult;

@RestController
@RequestMapping("/studentExamAnswer")
public class StudentExamAnswersController {
	@Autowired
	private StudentExamAnswersService seas;
	@Autowired
	private ExamsService examService;

//----------------------------- ADD A STUDENT EXAM ANSWER ------------------------------------------------------	
	@PostMapping("/add")
	public ResponseEntity<?> addQuestion(@RequestBody StudentExamAnswer sea) {
		try {
			seas.addStudentExamAnswer(sea);
			return CreateResult.createSuccessResult("Student Answer Inserted Successfully", HttpStatus.CREATED);
		} catch (Exception ex) {
			return CreateResult.createErrorResult(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

//----------------------------- ADD All STUDENT_EXAM_ANSWERS ------------------------------------------------------	
	@PostMapping("/addAll")
	public ResponseEntity<?> addQuestion(@RequestBody List<StudentExamAnswer> sea) {
		try {
			seas.addAllStudentExamAnswers(sea);
			return CreateResult.createSuccessResult("Successfully submitted responses", HttpStatus.CREATED);
		} catch (Exception ex) {
			return CreateResult.createErrorResult(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

//----------------------------- GET STUDENT SCORE IN AN EXAM ------------------------------------------------------	
	@GetMapping("/getScore/student/{studentId}/exam/{examId}")
	public ResponseEntity<?> getScore(@PathVariable Long studentId, @PathVariable Long examId) {
		try {
			List<StudentExamAnswer> seaList = seas.getByStudentAndExam(studentId, examId);
			Exam exam = examService.findById(examId);
			if (exam != null) {
				int marks = 0;
				for (int i = 0; i < seaList.size(); i++) {
					if (seaList.get(i).getSelectedAnswer() == seaList.get(i).getQuestion().getAnswer()) {
						marks++;
					}
				}
				Map<String, Object> score = new HashMap<String, Object>();
				score.put("correctAnswers", marks);
				score.put("totalQuestions", seaList.size());
				return CreateResult.createSuccessResult(score, HttpStatus.CREATED);
			} else
				return CreateResult.createSuccessResult("Exam with exam id = " + examId + " does not exist",
						HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return CreateResult.createErrorResult(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

//----------------------------- CHECK IF STUDENT HAS ATTEMPTED AN EXAM ------------------------------------------------------	
	@GetMapping("/hasAttempted/student/{studentId}/exam/{examId}")
	public ResponseEntity<?> hasAttempted(@PathVariable Long studentId, @PathVariable Long examId) {
		try {
			List<StudentExamAnswer> seaList = seas.getByStudentAndExam(studentId, examId);
			boolean hasAttempted;
			if (seaList.isEmpty()) {
				hasAttempted = false;
			} else
				hasAttempted = true;
			return CreateResult.createSuccessResult(hasAttempted, HttpStatus.CREATED);
		} catch (Exception ex) {
			return CreateResult.createErrorResult(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

//----------------------------- STUDENT EXAM DETAILS ------------------------------------------------------	
	@GetMapping("/examDetails/student/{studentId}/exam/{examId}")
	public ResponseEntity<?> examDetails(@PathVariable Long studentId, @PathVariable Long examId) {
		try {
			List<StudentExamAnswer> seaList = seas.getByStudentAndExam(studentId, examId);
			List<HashMap<String, Object>> examDetailsList = new ArrayList<HashMap<String, Object>>();
			for (int i = 0; i < seaList.size(); i++) {
				StudentExamAnswer sea = seaList.get(i);
				Map<String, Object> examDetails = new HashMap<String, Object>();
				examDetails.put("question", sea.getQuestion().getQuestion());
				examDetails.put("optionA", sea.getQuestion().getOptionA());
				examDetails.put("optionB", sea.getQuestion().getOptionB());
				examDetails.put("optionC", sea.getQuestion().getOptionC());
				examDetails.put("optionD", sea.getQuestion().getOptionD());
				examDetails.put("correctAnswer", sea.getQuestion().getAnswer());
				if (Character.toUpperCase(sea.getSelectedAnswer()) == 'X') {
					examDetails.put("submittedAnswer", "Not attempted");
				} else
					examDetails.put("submittedAnswer", sea.getSelectedAnswer());
				examDetails.put("answerDescription", sea.getQuestion().getAnswerDesc());
				examDetailsList.add((HashMap<String, Object>) examDetails);
			}
			return CreateResult.createSuccessResult(examDetailsList, HttpStatus.CREATED);
		} catch (Exception ex) {
			return CreateResult.createErrorResult(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// ----------------------------- GET DETAILS OF ALL EXAMS ATTEMPTED BY A STUDENT ------------------------------------------------------
	@GetMapping("/getDetails/student/{studentId}")
	public ResponseEntity<?> getScore(@PathVariable Long studentId) {
		try {
			return CreateResult.createSuccessResult(seas.getAllExamDetails(studentId), HttpStatus.CREATED);
		} catch (Exception ex) {
			return CreateResult.createErrorResult(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

//----------------------------- GET MODULE WISE ANALYSIS ------------------------------------------------------	
	@GetMapping("/moduleAnalysis/student/{studentId}")
	public ResponseEntity<?> moduleAnalysis(@PathVariable Long studentId) {
		try {
			return CreateResult.createSuccessResult(seas.getModuleWiseAnalysis(studentId), HttpStatus.CREATED);
		} catch (Exception ex) {
			return CreateResult.createErrorResult(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

//----------------------------- GET TOPIC WISE ANALYSIS ------------------------------------------------------	
	@GetMapping("/topicAnalysis/student/{studentId}/module/{moduleId}")
	public ResponseEntity<?> topicAnalysis(@PathVariable Long studentId, @PathVariable Long moduleId) {
		try {
			return CreateResult.createSuccessResult(seas.getTopicWiseAnalysis(studentId, moduleId), HttpStatus.CREATED);
		} catch (Exception ex) {
			return CreateResult.createErrorResult(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
//----------------------------- GET ALL ATTEMPTED MODULES ------------------------------------------------------	
		@GetMapping("/attemptedModules/student/{studentId}")
		public ResponseEntity<?> topicAnalysis(@PathVariable Long studentId) {
			try {
				return CreateResult.createSuccessResult(seas.getAllAttemptedModules(studentId), HttpStatus.CREATED);
			} catch (Exception ex) {
				return CreateResult.createErrorResult(ex, HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}
}
