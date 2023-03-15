package com.sunbeam.DTO;

public class HospitalDto {
	////id,Regid,HospitalName,comments
	
	private int id;
	
	
	
	private String HospitalName;
	
	private String comments;
	
	public HospitalDto()
	{
		
	}

	public HospitalDto(int id, String hospitalName, String comments) {
		super();
		this.id = id;
		this.HospitalName = hospitalName;
		this.comments = comments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHospitalName() {
		return HospitalName;
	}

	public void setHospitalName(String hospitalName) {
		HospitalName = hospitalName;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "HospitalDto [id=" + id + ", HospitalName=" + HospitalName + ", comments=" + comments + "]";
	}

	
	
}
