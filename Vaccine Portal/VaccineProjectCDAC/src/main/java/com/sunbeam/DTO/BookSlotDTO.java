package com.sunbeam.DTO;

import java.util.Date;

//id | Regid | name   | center          | vaccine | date       | mobile
public class BookSlotDTO {
	
	private int id;
	
	
	private int Regid;
	
	
	private String firstName;
	
	private String lastName;
	
	private int centerid;
	
	private String vaccine;
	
	private Date date;
	
	private String mobile;
	
	
	public BookSlotDTO()
	{
		
	}


	public BookSlotDTO(int id, int regid, String firstName, String lastName, int centerid, String vaccine, Date date,
			String mobile) {
		super();
		this.id = id;
		this.Regid = regid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.centerid = centerid;
		this.vaccine = vaccine;
		this.date = date;
		this.mobile = mobile;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getRegid() {
		return Regid;
	}


	public void setRegid(int regid) {
		Regid = regid;
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


	public int getCenterid() {
		return centerid;
	}


	public void setCenterid(int centerid) {
		this.centerid = centerid;
	}


	public String getVaccine() {
		return vaccine;
	}


	public void setVaccine(String vaccine) {
		this.vaccine = vaccine;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	@Override
	public String toString() {
		return "BookSlotDTO [id=" + id + ", Regid=" + Regid + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", centerid=" + centerid + ", vaccine=" + vaccine + ", date=" + date + ", mobile=" + mobile + "]";
	}


	
	

}
