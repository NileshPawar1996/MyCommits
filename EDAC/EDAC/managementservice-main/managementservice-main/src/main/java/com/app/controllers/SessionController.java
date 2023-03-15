package com.app.controllers;

import java.util.List;

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
import com.app.service.SessionService;
import com.app.utils.Message;
import com.app.dtos.session.GetAllSessionsAndAllModulesByModuleIdDto;
import com.app.dtos.session.SessionInDescOrderDto;
import com.app.pojos.Session;

@RestController
@RequestMapping("/session")
public class SessionController {
	@Autowired
	SessionService sessionService;
	
	@PostMapping("/add")
	public ResponseEntity<Object> addModule(@RequestBody Session session){
		try {
			sessionService.addSession(session);
			return Message.getSuccessMessage("Session Added Successfully", HttpStatus.CREATED);
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/desc_order/{number}")
	public ResponseEntity<Object> getSessionByModuleIdAndBatchId(@PathVariable int number){
		try {
			List<SessionInDescOrderDto> session = sessionService.getSessionInDescendingOrder(number);
			if(session != null)
				return Message.getSuccessMessage(session, HttpStatus.OK);
			else	
				return Message.getRuntimeErrorMessage("Invalid Batch Id And Module Id", HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> editModule(@PathVariable Long id, @RequestBody Session session){
		try {
			String response = sessionService.editSession(id, session);
			if(response == "success") {
				return Message.getSuccessMessage("Session Edited Successfully", HttpStatus.ACCEPTED);
			}
			else {
				return Message.getRuntimeErrorMessage("Session Does Not Exists", HttpStatus.NOT_FOUND);
			}
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<Object> getAllSessions(){
		try {
			return Message.getSuccessMessage(sessionService.getAllSessions(), HttpStatus.OK);
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getSession(@PathVariable Long id){
		try {
			Session session = sessionService.getSession(id);
			if(session != null)
				return Message.getSuccessMessage(session, HttpStatus.OK);
			else
				return Message.getRuntimeErrorMessage("Invalid Session Id", HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/module/{id}")
	public ResponseEntity<Object> getSessionByModuleId(@PathVariable Long id){
		try {
			List<Session> session = sessionService.getSessionByModuleId(id);
			if(session != null)
				return Message.getSuccessMessage(session, HttpStatus.OK);
			else
				return Message.getRuntimeErrorMessage("Invalid Module Id", HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{courseId}/{batchId}")
	public ResponseEntity<Object> getSessionAndModulesByBatchIdForAttendance(@PathVariable String courseId, @PathVariable Long batchId){
		try {
			List<?> session = sessionService.getSessionAndModule(courseId, batchId);
			if(session != null)
				return Message.getSuccessMessage(session, HttpStatus.OK);
			else
				return Message.getRuntimeErrorMessage("Invalid Batch Id", HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/session_by_batch_module/{batchId}/{moduleId}")
	public ResponseEntity<Object> getSessionByModuleIdAndBatchId(@PathVariable Long batchId, @PathVariable Long moduleId){
		try {
			List<Session> session = sessionService.getSessionByModuleIdAndBatchId(moduleId, batchId);
			if(session != null)
				return Message.getSuccessMessage(session, HttpStatus.OK);
			else
				return Message.getRuntimeErrorMessage("Invalid Batch Id And Module Id", HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteSession(@PathVariable Long id){
		try {			
			String status = sessionService.deleteSession(id);
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
