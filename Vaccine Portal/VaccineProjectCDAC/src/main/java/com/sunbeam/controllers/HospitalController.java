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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.DTO.HospitalDto;
import com.sunbeam.DTO.Response;
import com.sunbeam.entities.HospitalDetail;
import com.sunbeam.services.HospitalDaoService;

@CrossOrigin
@RestController
public class HospitalController {
	
	
	@Autowired
	private HospitalDaoService service;
	
	@GetMapping("/Hospital/Find")
	public ResponseEntity<?> findAllDetails()
	{
		try
		{
			List<HospitalDetail> result=service.findAllDetails();
			return Response.success(result);
		}catch (Exception ex)
		{
			return Response.error(ex);
		}
	}
	
	
	
	@PostMapping("/Hospital")
	public ResponseEntity<?> AddHospitalDetail(@RequestBody HospitalDto hosdto)
	{
		System.out.println("inserted Id" +hosdto);
		Map<String, Object> result=service.addHospitalDetail(hosdto);
		return Response.success(result);
	}
	
	/*
	 * @DeleteMapping("/bookslot/{id}")
	public ResponseEntity<?> DeleteBookSlot(@PathVariable ("id") int id)
	{
		Map<String, Object> result=bookService.DeleteBookSlot(id);
		return Response.success(result);
	}
	 */
	@DeleteMapping("/HospitalReview/{id}")
	public ResponseEntity<?> DeleteHospitalREview(@PathVariable ("id") int id)
	{
		Map<String, Object> result=service.DeleteHospitalDetail(id);
		return Response.success(result);
	}

}
