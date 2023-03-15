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
@Table(name="bookslot")
public class BookSlot {
	//id | Regid | name   | center          | vaccine | Date       | mobile
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column
	private int id;
	
	@Column
	private int Regid;
	
	@Column
	private String firstName;
	
	
	@Column
	private String lastName;
	
	
	@Column
	private int centerid;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column
	private String mobile;
	
	
	public BookSlot()
	{
		
	}


	public BookSlot(int id, int regid, String firstName, String lastName, int centerid, Date date, String mobile) {
		super();
		this.id = id;
		this.Regid = regid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.centerid = centerid;
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
		return "BookSlot [id=" + id + ", Regid=" + Regid + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", centerid=" + centerid + ", date=" + date + ", mobile=" + mobile + "]";
	}


	
    

	
}
