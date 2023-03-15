package com.sunbeam.services;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunbeam.DTO.DTOEntityConverter;
import com.sunbeam.DTO.HospitalDto;
import com.sunbeam.daos.HospitalDao;
import com.sunbeam.entities.HospitalDetail;

@Transactional
@Service
public class HospitalDaoService {

	
	@Autowired
	private HospitalDao hospitaldao;
	
	@Autowired
	private DTOEntityConverter converter;
	
	
	public List<HospitalDetail> findAllDetails()
	{
		return hospitaldao.findAll();
	}
	
	
	public Map<String, Object> addHospitalDetail(HospitalDto hospitaldto)
	{
		HospitalDetail hosdetail=converter.toHospitalEntity(hospitaldto);
		hosdetail=hospitaldao.save(hosdetail);
		return Collections.singletonMap("Inserted Id", hosdetail.getId());
	}
	
	
	
	public Map<String,Object> DeleteHospitalDetail(int id)
	{
		if(hospitaldao.existsById(id))
		{
			hospitaldao.deleteById(id);
			return Collections.singletonMap("affectedRows", 1);
		}
		 return Collections.singletonMap("affectedRows", 0);
	}
	
	
	
}
