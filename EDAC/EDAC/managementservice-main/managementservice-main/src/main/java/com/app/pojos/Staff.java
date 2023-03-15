// //created by Aditya Shinde
package com.app.pojos;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name = "staff")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "staff_id")
	private Long id;

	@Column(name = "first_name", length = 50 , nullable = false)
	private String firstName;

	@Column(name = "last_name", length = 50 )
	private String lastName;

	@Column(name = "email", length = 50, unique = true, nullable = false)
	private String email;
	
	@Column(name = "password", length = 50, nullable = false)
	private String password;
	
	@Embedded
	@Column(name = "contact_details")
	private ContactDetails contactDetails;
	
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private Role role; 
	
	@Column(name = "salary")
	private double salary;
	
	@Column(name = "experience")
	private Integer experience; //in months
	
	@Column(name = "join_date")
	private LocalDate joinDate;
	
}
