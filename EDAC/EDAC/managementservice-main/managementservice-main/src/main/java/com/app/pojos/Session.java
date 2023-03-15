// Sahil M
package com.app.pojos;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "sessions")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Session {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="session_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="fk_batch_id")	
	private Batch batch;
	
	@ManyToOne
	@JoinColumn(name="fk_module_id")
	private com.app.pojos.Module module;
	
	@ManyToOne
	@JoinColumn(name="fk_staff_id")
	private Staff staff;
	
	private LocalDate date;	
	
	@Column(name="start_time", nullable = false)
	private LocalTime startTime;
	
	@Column(name="end_time", nullable = false)
	private LocalTime endTime;	
}
