package com.sunbeam.services;

import java.util.List;

import com.sunbeam.pojos.Quote;

public interface QuoteService {
	String saveQuote(Quote quote);

	List<Quote> getAllQuotes();

	List<Quote>  getAllQuotesByUser(Long userId);

	List<Quote> getAllFavQuotesOfUser(Long userId);

}
