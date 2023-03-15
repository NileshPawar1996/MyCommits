package com.sunbeam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.pojos.Quote;
import com.sunbeam.services.QuoteDetailsService;
import com.sunbeam.services.QuoteService;

@CrossOrigin
@RestController
@RequestMapping("/quote")
public class QuoteController {

	@Autowired
	QuoteService quoteService;

	@Autowired
	QuoteDetailsService quoteDetailsService;

	@GetMapping("/all")
	public ResponseEntity<?> getAllQuotes() {
		return new ResponseEntity<>(quoteService.getAllQuotes(), HttpStatus.OK);
	}

	@GetMapping("/myall/{userId}")
	public ResponseEntity<?> getUserQuotes(@PathVariable Long userId) {
		return new ResponseEntity<>(quoteService.getAllQuotesByUser(userId), HttpStatus.OK);
	}
	@GetMapping("/myfav/{userId}")
	public ResponseEntity<?> getUserFavouriteQuotes(@PathVariable Long userId) {
		return new ResponseEntity<>(quoteService.getAllFavQuotesOfUser(userId), HttpStatus.OK);
	}

	
	@PostMapping("/add")
	public ResponseEntity<?> registerNewUser(@RequestBody Quote quote) {
		return new ResponseEntity<>(quoteService.saveQuote(quote), HttpStatus.CREATED);
	}

	@PostMapping("/toggle/{userId}/{quoteId}")
	public ResponseEntity<?> toggleFavourite(@PathVariable Long userId, @PathVariable Long quoteId,
			@RequestBody boolean isFav) {
		return new ResponseEntity<>(quoteDetailsService.toggleFavourite(userId, quoteId, isFav), HttpStatus.OK);
	}

}