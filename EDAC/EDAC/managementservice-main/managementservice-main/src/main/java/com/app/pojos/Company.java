//created by Aditya Shinde
package com.app.pojos;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="companies")
@Getter
@Setter
@ToString
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "company_id")
	private Long id;
	@Column(name = "company_name", length = 50, nullable = false, unique = true)
	private String companyName;
}
