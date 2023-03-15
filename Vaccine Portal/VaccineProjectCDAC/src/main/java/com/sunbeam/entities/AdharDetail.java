package com.sunbeam.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="aadhaardetail")
public class AdharDetail {
//id | Regid | name   | address                        | gender | dob        | AadhaarNo

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column
	private int id;
	
	@Column
	private String name;
	
	
	@Column
	private int Regid;
	
	@Column
	private String address;
	
	@Column
	private String gender;
	@Column
	@Temporal(TemporalType.DATE)
	private Date dob;
	@Column
	private String AadhaarNo;
	
	
	public AdharDetail()
	{
		
	}


	public AdharDetail(int id, String name, int regid, String address, String gender, Date dob, String aadhaarNo) {
		super();
		this.id = id;
		this.name = name;
		this.Regid = regid;
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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getRegid() {
		return Regid;
	}


	public void setRegid(int regid) {
		Regid = regid;
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
		return "AdharDetail [id=" + id + ", name=" + name + ", Regid=" + Regid + ", address=" + address + ", gender="
				+ gender + ", dob=" + dob + ", AadhaarNo=" + AadhaarNo + "]";
	}

   
     
	
	
}
