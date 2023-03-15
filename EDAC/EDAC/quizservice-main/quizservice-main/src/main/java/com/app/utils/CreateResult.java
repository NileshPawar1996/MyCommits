package com.app.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CreateResult {
	
	public static ResponseEntity<Object> createSuccessResult(Object data, HttpStatus code) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "success");
		map.put("data", data);
		return new ResponseEntity<Object>(map, code);	
	}
	public static ResponseEntity<Object> createErrorResult(Object data, HttpStatus code){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "error");
		map.put("data", data);
		return new ResponseEntity<Object>(map,code);
	}
}
