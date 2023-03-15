package com.sunbeam.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeam.entities.BookSlot;

public interface BookSlotDao extends JpaRepository<BookSlot, Integer> {
     
	BookSlot findById(int id);
}
