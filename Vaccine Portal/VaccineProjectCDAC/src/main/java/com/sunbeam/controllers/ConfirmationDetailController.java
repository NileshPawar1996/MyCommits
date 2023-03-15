package com.sunbeam.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.DTO.AadharDetailDto;
import com.sunbeam.DTO.ConfirmationDetailDto;
import com.sunbeam.DTO.Response;
import com.sunbeam.entities.AdharDetail;
import com.sunbeam.entities.ConfirmationDetail;
import com.sunbeam.services.ConfirmationService;

@CrossOrigin
@RestController
public class ConfirmationDetailController {
	
	@Autowired
	private ConfirmationService service;
	   
	
	////////////////////////////////////////////////////////////////////////////
	@GetMapping("/confirm/find")
	
	public ResponseEntity<?> findAllDetails()
	{
		try 
		{
			
			List<ConfirmationDetail> result=service.findAllDetails();
			return Response.success(result);
			
		}catch(Exception ex)
		{
			return Response.error(ex);
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////
	
	
	
	@PostMapping("/Confirm")
	public ResponseEntity<?> AddConfirmDetails(@RequestBody ConfirmationDetailDto confirmdto)
	{
		System.out.println("inserted" +confirmdto);
		Map<String,Object> result=service.addConfirmDetail(confirmdto);
		return Response.success(result);
	}
    /////////////////////////////////////////////////////////////////////////////////
	
	
	@PutMapping("/confirm/{id}")
	public ResponseEntity<?> editConfirmDetail(@PathVariable ("id") int id,@RequestBody ConfirmationDetailDto confirmdto)
	{
		Map<String,Object> result=service.EditConfirmDetail(id, confirmdto);
		return Response.success(result);
	}
	////////////////////////////////////////////////////////////////////////////////
	
	
	
	@DeleteMapping("/confirm/{id}")
	public ResponseEntity<?> DeleteBookId(@PathVariable ("id") int id)
	{
		Map<String,Object> result=service.DeleteConfirmDetail(id);
		return Response.success(result);
	}
	//////////////////////////////////////////////////////////////////////////////
}
