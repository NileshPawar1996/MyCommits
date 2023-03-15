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
import com.sunbeam.DTO.VaccinationReviewDto;
import com.sunbeam.entities.AdharDetail;
import com.sunbeam.entities.VaccinationReview;
import com.sunbeam.services.VaccinationReviewService;

@CrossOrigin
@RestController
public class VaccineReviewController {
	
	@Autowired
	private VaccinationReviewService service;
	
	
	/////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/Review/find")
	public ResponseEntity<?> findAllDetails()
	{
		try
		{
			List<VaccinationReview> result=service.findAllDetails();
			return Response.success(result);
		}
		catch(Exception ex)
		{
			return Response.error(ex);
		}
	}
    //////////////////////////////////////////////////////////////////////////////
	
	
	@PostMapping("/VaccineReview")
	public ResponseEntity<?> AddVaccineReview(@RequestBody VaccinationReviewDto vaccineDto)
	{
		System.out.println("inserted " +vaccineDto);
		Map<String, Object> result=service.addVaccinationReview(vaccineDto);
		return Response.success(result);
	}
	//////////////////////////////////////////////////////////////////////////////
	
	
	@PutMapping("/Review/{id}")
	public ResponseEntity<?> EditReviewDetail(@PathVariable ("id") int id,@RequestBody VaccinationReviewDto vaccinedto)
	{
		Map<String,Object> result=service.EditVaccinationReview(id, vaccinedto);
		return Response.success(result);
	}
	
	///////////////////////////////////////////////////////////////////////////////
	
	
	@DeleteMapping("/vaccinereview/{id}")
	public ResponseEntity<?> DeleteVaccineREviews(@PathVariable ("id") int id)
	{
		Map<String, Object> result=service.DeleteVaccineReview(id);
		return Response.success(result);
	}
}
