	//created by Sahil Dugaje
package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "batches", uniqueConstraints = { @UniqueConstraint(columnNames = { "fk_course_id", "batch_id" }) })
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Batch {
	
	@Id
	@Column(name = "batch_surrogate_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "batch_id", columnDefinition = "char(7)", unique = true)
	private String batchId;		
	
	@JoinColumn(name = "fk_course_id")
	@ManyToOne	
	private Course course;
	
	@Column(name = "max_strength", nullable = true)
	private Integer maxStrength;
}
