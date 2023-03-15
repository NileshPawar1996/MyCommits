package com.sunbeam.DTO;

import java.util.Date;

//id | Regid | name   | address| gender | dob        | AadhaarNo
public class AadharDetailDto {
	
	private int id;
	
	private int Regid;
	
	private String name;
	
	private String address;
	
	private String gender;
	
	private Date dob;
	
	private String AadhaarNo;
	
	
	public AadharDetailDto()
	{
		
	}


	public AadharDetailDto(int id, int regid, String name, String address, String gender, Date dob, String aadhaarNo) {
		super();
		this.id = id;
		this.Regid = regid;
		this.name = name;
		this.address = address;
		this.gender = gender;
		this.dob = dob;
		this.AadhaarNo = aadhaarNo;
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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public Date getDob() {
		return dob;
	}


	public void setDob(Date dob) {
		this.dob = dob;
	}


	public String getAadhaarNo() {
		return AadhaarNo;
	}


	public void setAadhaarNo(String aadhaarNo) {
		AadhaarNo = aadhaarNo;
	}


	@Override
	public String toString() {
		return "AadharDetailDto [id=" + id + ", Regid=" + Regid + ", name=" + name + ", address=" + address
				+ ", gender=" + gender + ", dob=" + dob + ", AadhaarNo=" + AadhaarNo + "]";
	}


	
	

}
