package com.sunbeam.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="hospitaldetail")
public class HospitalDetail {
	//id,Regid,HospitalName,comments
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Id
		private int id;
		
		@Column
		private String HospitalName;
		@Column
		private String comments;
		
	      
		public HospitalDetail()
		{
			
		}


		public HospitalDetail(int id, String hospitalName, String comments) {
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
			return "HospitalDetail [id=" + id + ", HospitalName=" + HospitalName + ", comments=" + comments + "]";
		}

    
		
}
