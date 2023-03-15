package com.sunbeam.repos;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sunbeam.pojos.Quote;
import com.sunbeam.pojos.User;
@Repository
public interface QuoteRepo extends JpaRepository<Quote, Long> {
	List<Quote> findByUser(User user);

	@Query("SELECT q FROM Quote q JOIN q.quoteDetailsList qd JOIN qd.user u WHERE qd.isFav = true AND u = :user")
	List<Quote> findFavoriteQuotesByUser(@Param("user") User user);

}
