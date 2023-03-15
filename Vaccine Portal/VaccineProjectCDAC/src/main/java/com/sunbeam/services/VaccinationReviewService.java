package com.sunbeam.services;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunbeam.DTO.AadharDetailDto;
import com.sunbeam.DTO.DTOEntityConverter;
import com.sunbeam.DTO.VaccinationReviewDto;
import com.sunbeam.daos.VaccinationReviewDao;
import com.sunbeam.entities.AdharDetail;
import com.sunbeam.entities.VaccinationReview;

@Transactional
@Service
public class VaccinationReviewService {
	
	@Autowired
	private VaccinationReviewDao vaccinedao;
	
	@Autowired 
	
	private DTOEntityConverter converter;
	
	

	/////////////////////////////////////////////////////////////////////////////
	public List<VaccinationReview> findAllDetails()
	{
		return vaccinedao.findAll();
	}
	////////////////////////////////////////////////////////////////////////////
	
	
	public Map<String , Object> addVaccinationReview(VaccinationReviewDto reviewDto)
	{
		VaccinationReview review=converter.toReviewEntity(reviewDto);
		review=vaccinedao.save(review);
		return Collections.singletonMap("Inserted Id", review.getId());
	}
	
	//////////////////////////////////////////////////////////////////////////////
	
	
	
	public Map<String,Object> EditVaccinationReview(int id,VaccinationReviewDto reviewdto)
	{
		if(vaccinedao.existsById(id))
		{
			reviewdto.setId(id);
			VaccinationReview review=converter.toReviewEntity(reviewdto);
			review=vaccinedao.save(review);
			return Collections.singletonMap("Changedrows", 1);
		}
		return Collections.singletonMap("changedRows", 0);
	}
	
	///////////////////////////////////////////////////////////////////////////////
	
	
	   public Map<String,Object> DeleteVaccineReview(int id)
	   {
		   if(vaccinedao.existsById(id))
		   {
			   vaccinedao.deleteById(id);
			   return Collections.singletonMap("affectedRows", 1);
		   }
		   return Collections.singletonMap("affectedRows", 0);
	   }
	
	
	

}
