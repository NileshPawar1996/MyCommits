package com.app.dtos.admin;

import java.time.LocalDate;

import com.app.pojos.ContactDetails;
import com.app.pojos.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
	
@Getter
@Setter
@AllArgsConstructor
public class GetAdminDetails {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private ContactDetails contactDetails;
	private Role role; 
	private double salary;
	private Integer experience;
	private LocalDate joinDate;
}
