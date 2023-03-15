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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.DTO.BookSlotDTO;
import com.sunbeam.DTO.Response;
import com.sunbeam.DTO.UserDto;
import com.sunbeam.entities.User;
import com.sunbeam.services.BookSlotService;
import com.sunbeam.services.UserserviceImpl;

@CrossOrigin
@RestController
@RequestMapping("/CUSTOMER")
public class CustomerController {
	@Autowired
	private BookSlotService bookService;
	
	  @Autowired
		private UserserviceImpl userservice;
	
	@PostMapping("/Customer/signup")
	
	public ResponseEntity<?> Signup(@RequestBody UserDto userDto)
	{
		
		Map<String,Object> result= userservice.SaveUser(userDto);
		return Response.success(result);
	}
	/*
	@PostMapping("/Bookslot/{id}")
	public ResponseEntity<?> BookSlot(@PathVariable ("id") int id,@RequestBody BookSlotDTO bookdto)
	{
		User user=userservice.findUserById(id);
		if(user.getMobileNumber().equals(bookdto.getMobile())) {
			System.out.println("Inserting" +bookdto);
			Map<String, Object> result=bookService.addSlot(bookdto);
			return Response.success(result);
		}else {
			return Response.error("Mobile number does not match , please enter correct number");
		}
		
		*/
	@PostMapping("/Bookslot")
	public ResponseEntity<?> BookSlot(@RequestBody BookSlotDTO bookdto)
	{
		System.out.println("Inserting" +bookdto);
		Map<String, Object> result=bookService.addSlot(bookdto);
		return Response.success(result);
	}
	
	///////////////////////////////////////////////////////////////////
	@GetMapping("/bookslot/find")
	public ResponseEntity<?> findAllDetails()
	{
		try 
		{
			List<com.sunbeam.entities.BookSlot> result=bookService.findAllDetails();
			return Response.success(result);
			
		}
		catch(Exception ex)
		{
			return Response.error(ex);
		}
	}
	
	////////////////////////////////////////////////////////////////////////////
	
	@PutMapping("/bookslot/{id}")
	public ResponseEntity<?> editBookSlot(@PathVariable ("id") int id,@RequestBody BookSlotDTO bookdto)
	{
		Map<String, Object> result= bookService.EditBookSlot(id, bookdto);
		return Response.success(result);
	}
   ///////////////////////////////////////////////////////////////////////////
	
	@DeleteMapping("/bookslot/{id}")
	public ResponseEntity<?> DeleteBookSlot(@PathVariable ("id") int id)
	{
		Map<String, Object> result=bookService.DeleteBookSlot(id);
		return Response.success(result);
	}
	
	////////////////////////////////////////////////////////////////////////
	

}
