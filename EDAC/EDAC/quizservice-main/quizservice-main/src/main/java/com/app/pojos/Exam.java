//Sahil Mulla
package com.app.pojos;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="exams")
@Getter
@Setter
@NoArgsConstructor
public class Exam {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "exam_id")
	private Long id;
	

	@Column(name="fk_batch_id")
	private Long batchId;
	
	@Column(name="fk_module_id")
	private Long moduleId;
	
	@Column(name = "exam_type")
	@Enumerated(EnumType.STRING)
	private ExamType type;
	
	@Column (name = "start_date_time", nullable = false)
	private LocalDateTime startDateTime;
	@Column (name = "end_date_time", nullable = false)
	private LocalDateTime endDateTime;
	
	@Column(name = "max_marks")
	private Integer maxMarks;
}
