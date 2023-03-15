package com.app.utils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Message {
	public static ResponseEntity<Object> getSuccessMessage(Object obj, HttpStatus code){
		Map<String, Object> map = new HashMap<>();
		map.put("data", obj);
		map.put("status", "success");
		return new ResponseEntity<Object>(map, code);
	}
	public static ResponseEntity<Object> getErrorMessage(Throwable th, HttpStatus code){
		Map<String, Object> map = new HashMap<>();
		Error error = new Error();
		error.setErrorType("Server Error");
		error.setDateTime(LocalDateTime.now());
		error.setMessage(th.getCause().getCause().getLocalizedMessage());
		error.setStatusCode(code);
		map.put("status", "error");
		map.put("data", error);
		return new ResponseEntity<Object>(map, code);
	}
	
	public static ResponseEntity<Object> getRuntimeErrorMessage(String str, HttpStatus code){
		Map<String, Object> map = new HashMap<>();
		Error error = new Error();
		error.setErrorType("Client Error");
		error.setDateTime(LocalDateTime.now());
		error.setMessage(new RuntimeException(str).getMessage());
		error.setStatusCode(code);
		map.put("status", "error");
		map.put("data", error);
		return new ResponseEntity<Object>(map, code);
	}
}