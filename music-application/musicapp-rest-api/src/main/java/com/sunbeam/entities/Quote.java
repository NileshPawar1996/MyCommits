package com.sunbeam.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "quotes")
public class Quote extends BaseEntity {
	@NotNull(message = "Your quote cannot be blank")
	@Length(max = 200)
	@Column(name = "quote")
	private String quote;

	@NotNull(message = "Author cannot be blank")
	@Length(max = 30)
	private String author;

	@Column(name = "is_fav", columnDefinition = "BIT")
	private boolean isFav;
}
