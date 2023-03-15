package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.dtos.batch.GetBatchIdFromBody;
import com.app.dtos.module.StatusAndTeacherNameDto;
import com.app.pojos.Batch;
import com.app.service.BatchService;
import com.app.utils.Message;

@CrossOrigin
@RestController
@RequestMapping("/batch")
public class BatchController {
	@Autowired
	BatchService batchService;
	
	@PostMapping("/add")
	public ResponseEntity<Object> addCourse(@RequestBody Batch batch){
		try {
			batchService.addBatch(batch);	
			return Message.getSuccessMessage("Batch Added Successfully", HttpStatus.CREATED);
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<Object> getAllBatches(){
		try {
			return Message.getSuccessMessage(batchService.getAllBatches(), HttpStatus.OK);
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/get_by_batch")
	public ResponseEntity<Object> getByStringBatchId(@RequestBody GetBatchIdFromBody id){
		try {
			return Message.getSuccessMessage(batchService.getByBatch(id.getId()), HttpStatus.OK);
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> get(@PathVariable Long id){
		try {
			Batch batch = batchService.getById(id);
			if(batch != null)
				return Message.getSuccessMessage(batch, HttpStatus.OK);
			else
				return Message.getRuntimeErrorMessage("No batch Found With this id", HttpStatus.NOT_FOUND);
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/course/{courseId}")
	public ResponseEntity<Object> getBatchesByCourseId(@PathVariable String courseId){
		try {
			List<Batch> batches = batchService.getBatchesByCourseId(courseId);
			if(batches.size() != 0)
				return Message.getSuccessMessage(batches, HttpStatus.OK);
			else
				return Message.getRuntimeErrorMessage("Invaild Course Id", HttpStatus.NOT_FOUND);
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteBatch(@PathVariable Long id){
		try {			
			String status = batchService.deleteBatch(id);
			if(status == "success")	
				return Message.getSuccessMessage("Module Deleted Successfully", HttpStatus.OK);
			else
				return Message.getRuntimeErrorMessage("Invalid Module Id", HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
