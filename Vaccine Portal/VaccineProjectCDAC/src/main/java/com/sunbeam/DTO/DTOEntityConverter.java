package com.sunbeam.DTO;

import org.springframework.stereotype.Component;

import com.sunbeam.entities.AdharDetail;
import com.sunbeam.entities.BookSlot;
import com.sunbeam.entities.ConfirmationDetail;
import com.sunbeam.entities.HospitalDetail;
import com.sunbeam.entities.User;
import com.sunbeam.entities.VaccinationDetail;
import com.sunbeam.entities.VaccinationReview;

@Component
public class DTOEntityConverter {
    //first make dto and then convert into dtoconverter
	
	public UserDto toUserDTO (User entity)
	{
		
		////id | firstName | lastName | city  | email       | password
		UserDto dto =new UserDto();
		dto.setId(entity.getId());
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		dto.setCity(entity.getCity());
		
		dto.setMobileNumber(entity.getMobileNumber());
		dto.setRole(entity.getRole());
		return dto;
	}

	public User toUserEntity(UserDto dto) {
		User entity =new User();
		entity.setId(dto.getId());
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setCity(dto.getCity());
	
		entity.setPassword(dto.getPassword());
		entity.setRole(dto.getRole());
		entity.setMobileNumber(dto.getMobileNumber());
		return entity;
	}
	
	//write methods for other class like aadhar ,bookslot
	
	public BookSlot toBookEntity(BookSlotDTO dto)
	{
		////id | Regid | name   | center          | vaccine | date       | mobile
		BookSlot entity=new BookSlot();
		entity.setId(dto.getId());
	      entity.setRegid(dto.getRegid());
	     entity.setFirstName(dto.getFirstName());
	     entity.setLastName(dto.getLastName());
	     entity.setCenterid(dto.getCenterid());
	    
	     entity.setDate(dto.getDate());
	     entity.setMobile(dto.getMobile());
		return entity;
	     
	}
	
	
	public AdharDetail toAdharEntity(AadharDetailDto dto)
	{
		////id | Regid | name   | address| gender | dob        | AadhaarNo
		AdharDetail entity=new AdharDetail();
		entity.setId(dto.getId());
		entity.setRegid(dto.getRegid());
		entity.setName(dto.getName());
		entity.setAddress(dto.getAddress());
		entity.setGender(dto.getGender());
		entity.setDob(dto.getDob());
		entity.setAadhaarNo(dto.getAadhaarNo());
		return entity;
	}
	
	
	public ConfirmationDetail toConfirmEntity(ConfirmationDetailDto dto)
	{
		//id | Regid | name   | email       | mobile
		ConfirmationDetail entity=new ConfirmationDetail();
		 entity.setId(dto.getId());
		 
		 entity.setName(dto.getName());
		 entity.setEmail(dto.getEmail());
		 entity.setMobile(dto.getMobile());
		 return entity;
	}
	
	public VaccinationReview toReviewEntity(VaccinationReviewDto dto)
	{
		//id | Regid | VaccineName | comments
		VaccinationReview entity=new VaccinationReview();
		entity.setId(dto.getId());
		
		entity.setVaccineName(dto.getVaccineName());
		entity.setComments(dto.getComments());
		return entity;
	}
	
	public HospitalDetail toHospitalEntity(HospitalDto dto)
	{  
		
	////id,Regid,HospitalName,comments
		HospitalDetail entity=new HospitalDetail();
		 entity.setId(dto.getId());
		
		 entity.setHospitalName(dto.getHospitalName());
		 entity.setComments(dto.getComments());
		 return entity;
	}

	
}
