package com.app.dtos;

import java.time.LocalDate;

import com.app.pojos.ContactDetails;
import com.app.pojos.StudentPlacementDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
	
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private Long batchId;
	
	private String courseId;
	
	private ContactDetails contactDetails;
	
	private String photoUrl;
	
	private String aadharNo;
	
	private Integer overallMarks;
	
	private LocalDate dob;
	
	private StudentPlacementDetails companyDetails;
}
