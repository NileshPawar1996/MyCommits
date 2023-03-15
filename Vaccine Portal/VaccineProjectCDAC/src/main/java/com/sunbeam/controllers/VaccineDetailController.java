package com.sunbeam.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.DTO.Response;
import com.sunbeam.entities.VaccinationDetail;
import com.sunbeam.services.VaccinationDetailService;
//cross origin annotation imp for every controller
@CrossOrigin
@RestController
public class VaccineDetailController {
	
	
	@Autowired
	private VaccinationDetailService vaccineService;
	 
	
	
	@GetMapping("/vaccine/find")
	
	public ResponseEntity<?> FindAllDetails()
	{
		try
		{
			List<VaccinationDetail> result=vaccineService.FindAllDetails();
			return Response.success(result);
		}
		catch (Exception e) {
			return Response.error(e);
			}
	}

}
