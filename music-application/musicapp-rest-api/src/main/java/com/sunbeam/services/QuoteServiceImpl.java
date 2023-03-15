package com.sunbeam.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunbeam.exceptions.*;
import com.sunbeam.pojos.Quote;
import com.sunbeam.pojos.User;
import com.sunbeam.repos.QuoteDetailsRepo;
import com.sunbeam.repos.QuoteRepo;
import com.sunbeam.repos.UserRepo;

@Service
@Transactional
public class QuoteServiceImpl implements QuoteService {

	@Autowired
	QuoteRepo quoteRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	QuoteDetailsRepo qDetailsRepo;

	@Override
	public String saveQuote(Quote quote) {
		quoteRepo.save(quote);
		return "Quote saved successfully!!!";
	}

	@Override
	public List<Quote> getAllQuotes() {
		return quoteRepo.findAll();
	}

	@Override
	public List<Quote> getAllQuotesByUser(Long userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new CustomCentralException("User Id Invalid!"));
		return quoteRepo.findByUser(user);
	}

	@Override
	public List<Quote> getAllFavQuotesOfUser(Long userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new CustomCentralException("User Id Invalid!"));
		return quoteRepo.findFavoriteQuotesByUser(user);
	}
}