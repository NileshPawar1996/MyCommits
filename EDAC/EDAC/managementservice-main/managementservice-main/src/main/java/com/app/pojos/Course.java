//created by Sahil Dugaje
package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "courses")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Course {	
	@Id
	@Column(name = "course_id", length = 10)
	private String id;
	
	@Column(name = "course_name", nullable = false)
	private String courseName;
}
