//Sahil M
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
@Table(name = "attendance")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Attendance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="attendance_id")
	private Long id;
	
	@Column(name="fk_session_id")
	private Long sessionId;

	@ManyToOne
	@JoinColumn(name="fk_student_id")
	private Student student;
	
	@Column(nullable = false)
	private int duration;
}
