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
import com.sunbeam.DTO.Response;
import com.sunbeam.entities.AdharDetail;
import com.sunbeam.services.AadharDetailService;

@CrossOrigin
@RestController
public class AadharDetailController {
	
	@Autowired
	private AadharDetailService adharservice;
	///////////////////////////////////////////////////////////////////////////
	@GetMapping("/Aadhar/find")
	public ResponseEntity<?> findAllDetails()
	{
		try 
		{
			List<AdharDetail> result= adharservice.findAllDetails();
			return Response.success(result);
			
		}
		catch (Exception ex)
		{
			return Response.error(ex);
		}
	}
	////////////////////////////////////////////////////////////////////////////
	
	@PostMapping("/Aadhar")
	public ResponseEntity<?> AddAdharDetail(@RequestBody AadharDetailDto adhardto)
	{
		
		System.out.println("inserted" +adhardto);
		Map<String, Object> result=adharservice.addAdharDetail(adhardto);
		return Response.success(result);
		
	}
	
	//////////////////////////////////////////////////////////////////////////
	
	@PutMapping("/aadhar/{id}")
	public ResponseEntity<?> editAadharDetail(@PathVariable ("id") int id,@RequestBody AadharDetailDto adhardto)
	{
		Map<String,Object> result=adharservice.EditAdharDetail(id, adhardto);
		return Response.success(result);
	}
	
	//////////////////////////////////////////////////////////////////////////////
	
	/*
	 * @DeleteMapping("/bookslot/{id}")
	public ResponseEntity<?> DeleteBookSlot(@PathVariable ("id") int id)
	{
		Map<String, Object> result=bookService.DeleteBookSlot(id);
		return Response.success(result);
	}
	 */
	
	@DeleteMapping("/Aadhar/{id}")
	public ResponseEntity<?> DeleteAadharId(@PathVariable ("id") int id)
	{
		Map<String, Object> result= adharservice.DeleteAadharDetail(id);
		return Response.success(result);
	}

}
