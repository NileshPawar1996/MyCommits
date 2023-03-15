package com.sunbeam.DTO;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.sunbeam.entities.Role;

public class UserDto {
//id | firstName | lastName | city  | email       | password
	private int id;
	
	private String firstName;
	
	private String lastName;
	
	private String city;
	
	private String email;
	
	private String password;
	
	private String mobileNumber;
	
	

	@Enumerated(EnumType.STRING)
	private Role role;
	
	
	public UserDto()
	{
		
	}


	public UserDto(int id, String firstName, String lastName, String city, String email, String password,
			String mobileNumber, Role role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.email = email;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.role = role;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "UserDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", city=" + city
				+ ", email=" + email + ", password=" + password + ", mobileNumber=" + mobileNumber + ", role=" + role
				+ "]";
	}

	


}
