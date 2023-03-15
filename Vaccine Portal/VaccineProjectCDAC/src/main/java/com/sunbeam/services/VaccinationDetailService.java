package com.sunbeam.services;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunbeam.DTO.AadharDetailDto;
import com.sunbeam.DTO.BookSlotDTO;
import com.sunbeam.DTO.DTOEntityConverter;

import com.sunbeam.daos.VaccinationDetailDao;
import com.sunbeam.entities.AdharDetail;
import com.sunbeam.entities.BookSlot;
import com.sunbeam.entities.VaccinationDetail;

@Transactional
@Service
public class VaccinationDetailService {
	
	
	@Autowired
	private VaccinationDetailDao vaccineDao;
	
	
	@Autowired
	private DTOEntityConverter converter;
	
	public int getQuantity() {
	       return getQuantity();
	   }
	   
	   public int setQuantity(VaccinationDetail slot) {
	       return slot.setQuantity(slot.getQuantity()-1);
	       }

	
	
	public List<VaccinationDetail>  FindAllDetails()
	{   
		
		
		return vaccineDao.findAll();
	}


	/*
	 * public Map<String, Object> addSlot(BookSlotDTO bookslot)
	{
		BookSlot book= converter.toBookEntity(bookslot);
		book=bookdao.save(book);
		return Collections.singletonMap("Inserted Id", book.getId());
	}
	 */
    


  public Map<String, Object> addvaccinescenters(VaccinationDetail vacdetails) {
		
		VaccinationDetail vacdetail=vaccineDao.save(vacdetails);
		
		return Collections.singletonMap("Inserted Id", vacdetail.getCenterid());
	}

	/*
	 * public Map<String, Object> DeleteBookSlot(int id)
	{
		if(bookdao.existsById(id))
		{
			bookdao.deleteById(id);
			return Collections.singletonMap("affectedRows", 1);
		}
		
		return Collections.singletonMap("affectedRows", 0);
	}
	
	 */
  
  public Map<String,Object> DeleteVaccinationDetail(int id)
  {
	  if(vaccineDao.existsById(id))
	  {
		  vaccineDao.deleteById(id);
		  return Collections.singletonMap("affectedRows", 1);
	  }

		return Collections.singletonMap("affectedRows", 0);
  }
	

	public Map<String, Object> updateVaccinecenter(int id,VaccinationDetail vacdetails) {
		
		if(vaccineDao.existsById(id))
		{
		VaccinationDetail vacdetail=vaccineDao.save(vacdetails);
		
		return Collections.singletonMap("changed rows", 1);
	}
	return Collections.singletonMap("changedRows", 0);
	}

	
	  public VaccinationDetail FindById(int centerid) {
	 
	  
	  return vaccineDao.getById(centerid); }
	
}

