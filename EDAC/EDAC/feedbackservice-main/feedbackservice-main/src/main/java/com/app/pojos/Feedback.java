package com.app.pojos;
//aditya nagpure
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

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "feedbacks")
public class Feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "feedback_id")
	private Long id;
	
	@Column(name = "fk_batch_id", nullable = true, length = 10 )
	private String batchId;
	
	@Column(name = "fk_student_id", nullable = true)
	private Long studentId;
	
	@Column(name = "fk_module_id", nullable = true)
	private Long moduleId;
	
	@Column(name = "comment", length = 300)
	private String comment;
	
}
