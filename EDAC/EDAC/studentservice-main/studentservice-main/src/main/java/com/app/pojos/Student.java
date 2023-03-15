//created by : Aditya Nagpure
package com.app.pojos;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
//adityaN
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "students")
@ToString
@NoArgsConstructor
@Getter
@Setter
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private Long id;
	
	@Column(name = "first_name", length = 50, nullable = false)
	private String firstName;
	
	@Column(name = "last_name",length = 50, nullable = false)
	private String lastName;
	
	
	@Column(name = "email",length = 50, nullable = false, unique = true)
	private String email;
	
	@Column(name = "password",length = 50, nullable = false)
	private String password;
	
	@Column(name = "fk_batch_id")
	private Long batchId;
	
	@Column(name = "fk_course_id", length = 10)
	private String courseId;
	
	@Embedded
	private ContactDetails contactDetails;
	
	@Column(name = "photo_url")
	private String photoUrl;
	
	@Column(name = "aadhar_no", length = 12, nullable = false)
	private String aadharNo;
	
	private Integer overallMarks;
	
	private LocalDate dob;
	
	@Embedded
	private StudentPlacementDetails companyDetails;
}
