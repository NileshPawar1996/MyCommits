package com.sunbeam.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vaccinationdetail")
public class VaccinationDetail {
	
	// id | centerid | center          | vaccine | Quantity
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
   @Column
	private int centerid;
	@Column
	private String center;
	@Column
	private String vaccine;
	@Column
	private int quantity;
	
	
	public VaccinationDetail()
	{
		
	}


	public VaccinationDetail(int centerid, String center, String vaccine, int quantity) {
		super();
		this.centerid = centerid;
		this.center = center;
		this.vaccine = vaccine;
		this.quantity = quantity;
	}


	public int getCenterid() {
		return centerid;
	}


	public void setCenterid(int centerid) {
		this.centerid = centerid;
	}


	public String getCenter() {
		return center;
	}


	public void setCenter(String center) {
		this.center = center;
	}


	public String getVaccine() {
		return vaccine;
	}


	public void setVaccine(String vaccine) {
		this.vaccine = vaccine;
	}


	public int getQuantity() {
		return quantity;
	}


	public int setQuantity(int quantity) {
		return this.quantity = quantity;
	}


	@Override
	public String toString() {
		return "VaccinationDetail [centerid=" + centerid + ", center=" + center + ", vaccine=" + vaccine + ", quantity="
				+ quantity + "]";
	}


	   

}
