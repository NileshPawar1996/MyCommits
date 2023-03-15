package com.sunbeam.services;

public interface QuoteDetailsService {
	String toggleFavourite(Long userId, Long quoteId, boolean isfav);
}
