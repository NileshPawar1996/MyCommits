package com.sunbeam.DTO;
//email       | password
public class Credential {
 
	private String mobileNumber;
	
	private String password;
	
	private String email;
	
	public Credential()
	{
		
	}

	public Credential(String mobileNumber, String password,String email) {
		super();
		this.mobileNumber =  mobileNumber;
		this.password = password;
		this.email = email;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Credential [mobileNumber=" + mobileNumber + ", password=" + password + ", email=" + email + "]";
	}

	

	
	
	
}
