package com.sunbeam.services;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunbeam.DTO.AadharDetailDto;
import com.sunbeam.DTO.ConfirmationDetailDto;
import com.sunbeam.DTO.DTOEntityConverter;
import com.sunbeam.daos.confirmationdao;
import com.sunbeam.entities.AdharDetail;
import com.sunbeam.entities.ConfirmationDetail;

@Transactional
@Service
public class ConfirmationService {
	
	@Autowired
	private confirmationdao confirmdao;
	
	@Autowired
	private DTOEntityConverter converter;
	
	////////////////////////////////////////////////////////////////////////////
	
	
	
	public List<ConfirmationDetail> findAllDetails()
	{
		return confirmdao.findAll();
	}
	
   /////////////////////////////////////////////////////////////////////////////
	
	
	public Map<String, Object> addConfirmDetail(ConfirmationDetailDto confirmdto)
	{
		ConfirmationDetail confirm=converter.toConfirmEntity(confirmdto);
		confirm=confirmdao.save(confirm);
		return Collections.singletonMap("Inserted Id", confirm.getId());
	}
	
	////////////////////////////////////////////////////////////////////////////
	  
	
	
	public Map<String,Object> EditConfirmDetail(int id,ConfirmationDetailDto confirmdto)
	{
		if(confirmdao.existsById(id))
		{
			confirmdto.setId(id);
			ConfirmationDetail confirm=converter.toConfirmEntity(confirmdto);
			confirm=confirmdao.save(confirm);
			 return Collections.singletonMap("Changedrows", 1);
		}
		return Collections.singletonMap("changedRows", 0);
	}
	
	/////////////////////////////////////////////////////////////////////////////////
	
	
	public Map<String, Object> DeleteConfirmDetail(int id)
	{
		if(confirmdao.existsById(id))
		{
			confirmdao.deleteById(id);
			return Collections.singletonMap("affectedRows", 0);
		}
		return Collections.singletonMap("affectedRows", 0);
	}
	/////////////////////////////////////////////////////////////////////////////////
}
