package com.sunbeam.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "quote_details")
public class QuoteDetails extends BaseEntity {
	
	@OneToOne
	@JoinColumn(name = "quote_id")
	private Quote quote;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "is_fav", columnDefinition = "BIT")
	private boolean isFav;

}
