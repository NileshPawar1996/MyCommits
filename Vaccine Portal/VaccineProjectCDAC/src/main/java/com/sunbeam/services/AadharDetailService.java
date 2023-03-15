package com.sunbeam.services;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunbeam.DTO.AadharDetailDto;
import com.sunbeam.DTO.DTOEntityConverter;
import com.sunbeam.daos.AadhaarDao;
import com.sunbeam.entities.AdharDetail;

@Transactional
@Service
public class AadharDetailService {
	
	@Autowired
	private AadhaarDao aadhardao;
	
	@Autowired
	private DTOEntityConverter converter;
	
	///////////////////////////////////////////////////////////////////////////////
	
     public List<AdharDetail>  findAllDetails()
     {
    	 return aadhardao.findAll();
     }
  ///////////////////////////////////////////////////////////////////////////////
     
     
     public Map<String, Object> addAdharDetail(AadharDetailDto adhardto)
     {
    	 AdharDetail adhar=converter.toAdharEntity(adhardto);
    	  adhar=aadhardao.save(adhar);
    	  return Collections.singletonMap("Inserted Id", adhar.getId());
     }
     ////////////////////////////////////////////////////////////////////////////
     
     
     public Map<String, Object> EditAdharDetail(int id,AadharDetailDto adhardto)
     {
    	 if(aadhardao.existsById(id))
    	 {
    		 adhardto.setId(id);
    		AdharDetail adhar=converter.toAdharEntity(adhardto);
    		 adhar=aadhardao.save(adhar);
    		 return Collections.singletonMap("Changedrows", 1);
    	 }
    		return Collections.singletonMap("changedRows", 0);
     }
     
     ///////////////////////////////////////////////////////////////////////////
  
     
     public Map<String,Object> DeleteAadharDetail(int id)
     {
    	 if(aadhardao.existsById(id))
    	 {
    		 aadhardao.deleteById(id);
    		 return Collections.singletonMap("affectedRows", 1);
    	 }
    	 return Collections.singletonMap("affectedRows", 0);
     }
     
     
     
}
