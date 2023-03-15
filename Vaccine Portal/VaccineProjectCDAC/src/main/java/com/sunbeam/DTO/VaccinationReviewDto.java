package com.sunbeam.DTO;
//id | Regid | VaccineName | comments
public class VaccinationReviewDto {
	
	private int id;
	
	
	
	private String VaccineName;
	
	private String comments;
	
	public VaccinationReviewDto()
	{
		
	}

	public VaccinationReviewDto(int id, String vaccineName, String comments) {
		super();
		this.id = id;
		this.VaccineName = vaccineName;
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
		return "VaccinationReviewDto [id=" + id + ", VaccineName=" + VaccineName + ", comments=" + comments + "]";
	}

	
	
	
	
	

}
