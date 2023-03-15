package com.sunbeam.controllers;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.DTO.BookSlotDTO;
import com.sunbeam.DTO.Response;
import com.sunbeam.config.SecurityConfig;
import com.sunbeam.entities.AdharDetail;
import com.sunbeam.entities.Role;
import com.sunbeam.entities.User;
import com.sunbeam.entities.VaccinationDetail;
import com.sunbeam.services.UserserviceImpl;
import com.sunbeam.services.VaccinationDetailService;
@CrossOrigin  //It prevent JavaScript Code
@RestController
@RequestMapping("/ADMIN")
public class AdminController {

	@Autowired
	private VaccinationDetailService vacservice;
	
	@Autowired
	private UserserviceImpl userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/AddVaccineCenter")
	
	public ResponseEntity<?> addvaccinecenter(@RequestBody VaccinationDetail vacdetails)
	{
		System.out.println("Inserting " +vacdetails);
		Map<String, Object> result=vacservice.addvaccinescenters(vacdetails);
		return Response.success(result);
	}
	
	
	
	@GetMapping("/findvacdetail")
	public ResponseEntity<?> findAllDEtails ()
	{
		try 
		{
			List<VaccinationDetail> result=vacservice.FindAllDetails();
			return Response.success(result);
		}
		catch(Exception ex)
		{
			return Response.error(ex);
		}
	}
	
	@DeleteMapping("/vacdetails/{id}")
	public ResponseEntity<?> DeleteVaccineDetail(@PathVariable ("id") int id)
	{
		Map<String, Object> result=vacservice.DeleteVaccinationDetail(id);
		return Response.success(result);
	}
	
	@PutMapping("/vacdetail/{id}")
     public ResponseEntity<?> editVaccineDetail(@PathVariable ("id") int id,@RequestBody  VaccinationDetail vacdetails)
     {
		System.out.println("Updating" +vacdetails);
		Map<String, Object> result=vacservice.updateVaccinecenter(id, vacdetails);
		return Response.success(result);
     }
	@PostConstruct
	public void addAdminAtStartup(){
		User user =new User();
		user.setId(1);
		user.setCity("Nanded");
		user.setFirstName("Gorakhanath");
		user.setLastName("Lingayat");
		user.setMobileNumber("9689028684");
		user.setPassword(passwordEncoder.encode("123"));
		user.setRole(Role.ADMIN);
		userService.addAdmin(user);
		
	}
	
}
