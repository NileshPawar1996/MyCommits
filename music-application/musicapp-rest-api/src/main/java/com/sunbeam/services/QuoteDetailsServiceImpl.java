package com.sunbeam.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunbeam.exceptions.*;
import com.sunbeam.pojos.Quote;
import com.sunbeam.pojos.QuoteDetails;
import com.sunbeam.pojos.User;
import com.sunbeam.repos.QuoteDetailsRepo;
import com.sunbeam.repos.QuoteRepo;
import com.sunbeam.repos.UserRepo;
@Service
@Transactional
public class QuoteDetailsServiceImpl implements QuoteDetailsService {

	@Autowired
	QuoteRepo quoteRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	QuoteDetailsRepo qDetailsRepo;

	@Override
	public String toggleFavourite(Long userId, Long quoteId, boolean isfav) {
		User user = userRepo.findById(userId).orElseThrow(() -> new CustomCentralException("User Id Invalid!"));
		Quote quote = quoteRepo.findById(quoteId).orElseThrow(() -> new CustomCentralException("Invalid Quote ID!"));
		QuoteDetails qDetails = qDetailsRepo.findByUserAndQuote(user, quote);
		qDetails.setFav(isfav);
		return "Status changed";
	}

}
