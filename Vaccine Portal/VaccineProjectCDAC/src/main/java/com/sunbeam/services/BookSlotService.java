package com.sunbeam.services;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunbeam.DTO.BookSlotDTO;
import com.sunbeam.DTO.DTOEntityConverter;
import com.sunbeam.daos.BookSlotDao;
import com.sunbeam.entities.BookSlot;
import com.sunbeam.entities.VaccinationDetail;

@Transactional
@Service
public class BookSlotService {
	
	@Autowired
    private BookSlotDao bookdao;
	@Autowired
	private VaccinationDetailService vc;
	
	@Autowired
	private DTOEntityConverter converter;
	
	
	public Map<String, Object> addSlot(BookSlotDTO bookslot)
	{
		VaccinationDetail slot1 = vc.FindById(bookslot.getCenterid());
		int x = slot1.getQuantity();
	       if(x>0) {
	    	   BookSlot book = converter.toBookEntity(bookslot);
	       book=bookdao.save(book);
	       vc.setQuantity(slot1);
	       return Collections.singletonMap("Inserted Id", book.getId());
	       }else {
	           return null;
	       }
	       
	   }
	
	/////////////////////////////////////////////////////////////////////////
	
	public List<BookSlot> findAllDetails()
	{
		return bookdao.findAll();
	}
	 
	///////////////////////////////////////////////////////////////////////////
	
	public Map<String,Object> EditBookSlot(int id,BookSlotDTO bookdto)
	{
		if(bookdao.existsById(id))
		{
			bookdto.setId(id);
			BookSlot bookslot=converter.toBookEntity(bookdto);
			bookslot=bookdao.save(bookslot);
			return Collections.singletonMap("changed rows", 1);
		}
		return Collections.singletonMap("changedRows", 0);
	}
	//////////////////////////////////////////////////////////////////////////////
	
	
	public Map<String, Object> DeleteBookSlot(int id)
	{
		if(bookdao.existsById(id))
		{
			bookdao.deleteById(id);
			return Collections.singletonMap("affectedRows", 1);
		}
		
		return Collections.singletonMap("affectedRows", 0);
	}
	
	////////////////////////////////////////////////////////////////////////////
}
