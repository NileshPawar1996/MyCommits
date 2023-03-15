//created by : Rajvardhan Shinde
package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table (name = "modules")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Module {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "module_id")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "fk_course_id", nullable = false)
	private Course course;
	@Column(name = "module_name", length = 50)
	private String moduleName;
	@Column(name = "duration_hrs")
	private int durationHrs;	
}
