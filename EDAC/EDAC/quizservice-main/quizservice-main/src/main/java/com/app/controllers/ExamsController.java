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

import com.app.pojos.Exam;
import com.app.service.examsService.ExamsService;
import com.app.utils.CreateResult;

@RestController
@RequestMapping("/exam")
public class ExamsController {
	@Autowired
	private ExamsService examService;

//----------------------------- ADD AN EXAM ------------------------------------------------------	
	@PostMapping("/add")
	public ResponseEntity<?> addExam(@RequestBody Exam exam) {
		try {
			examService.addExam(exam);
			return CreateResult.createSuccessResult("Exam Added Successfully", HttpStatus.CREATED);
		} catch (Exception ex) {
			return CreateResult.createErrorResult(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//----------------------------- DELETE AN EXAM BY ID ------------------------------------------------------	
	@DeleteMapping("/delete/{examId}")
	public ResponseEntity<?> deleteExam(@PathVariable("examId") Long examId) {
		try {
			examService.deleteExam(examId);
			return CreateResult.createSuccessResult("Exam Deleted Successfully", HttpStatus.CREATED);
		} catch (Exception ex) {
			return CreateResult.createErrorResult(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//----------------------------- GET FIRST EXAMS BY CREATION DATE (DESC) ------------------------------------------------------	
	@GetMapping("/get")
	public ResponseEntity<?> getExams() {
		try {
			List<Exam> elist = examService.getExamsDesc();
			return CreateResult.createSuccessResult(elist, HttpStatus.CREATED);
		} catch (Exception ex) {
			return CreateResult.createErrorResult(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//----------------------------- GET AN EXAM BY EXAM ID ------------------------------------------------------	
	@GetMapping("/get/{examId}")
	public ResponseEntity<?> getByExamId(@PathVariable Long examId) {
		try {
			Exam e = examService.getExamById(examId);
			if (e != null) {
				return CreateResult.createSuccessResult(e, HttpStatus.CREATED);
			} else
				return CreateResult.createSuccessResult("Such exam does not exist", HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return CreateResult.createErrorResult(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//----------------------------- GET ALL EXAMS BY BATCH ID ------------------------------------------------------	
	@GetMapping("/get/batch/{batchId}")
	public ResponseEntity<?> getByBatch(@PathVariable Long batchId) {
		try {
			List<Exam> e = examService.getByBatch(batchId);
			if (e != null) {
				return CreateResult.createSuccessResult(e, HttpStatus.CREATED);
			} else
				return CreateResult.createSuccessResult("Such exam does not exist", HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return CreateResult.createErrorResult(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
//----------------------------- GET FIRST EXAMS BY CREATION DATE (DESC) ------------------------------------------------------	
		@GetMapping("/get/beforeStart")
		public ResponseEntity<?> getExamsBeforeStartDate() {
			try {
				List<Exam> elist = examService.getExamsBeforeStartDesc();
				return CreateResult.createSuccessResult(elist, HttpStatus.CREATED);
			} catch (Exception ex) {
				return CreateResult.createErrorResult(ex, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
}
