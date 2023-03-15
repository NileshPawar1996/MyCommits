package com.sunbeam.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunbeam.pojos.Quote;
import com.sunbeam.pojos.QuoteDetails;
import com.sunbeam.pojos.User;
@Repository
public interface QuoteDetailsRepo extends JpaRepository<QuoteDetails, Long> {

	QuoteDetails findByUserAndQuote(User user, Quote quote);
}
