package com.sunbeam.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
// id | Regid | VaccineName | comments
@Entity
@Table(name="vaccinationReview")
public class VaccinationReview {
     
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column
	private int id;
	
	@Column
	private String VaccineName;
	@Column
	private String comments;
	
	public VaccinationReview()
	{
		
	}

	public VaccinationReview(int id, String vaccineName, String comments) {
		super();
		this.id = id;
		VaccineName = vaccineName;
		this.comments = comments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVaccineName() {
		return VaccineName;
	}

	public void setVaccineName(String vaccineName) {
		VaccineName = vaccineName;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "VaccinationReview [id=" + id + ", VaccineName=" + VaccineName + ", comments=" + comments + "]";
	}

	
	
}
