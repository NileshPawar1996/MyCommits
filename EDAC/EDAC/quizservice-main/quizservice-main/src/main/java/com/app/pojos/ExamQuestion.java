//CREATED BY RAJVARDHAN SHINDE
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
@Table (name = "exam_questions", uniqueConstraints = @UniqueConstraint(columnNames = { "fk_exam_id", "fk_question_id" }))
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ExamQuestion {      
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "exam_question_id")
	Long id;
	
	@ManyToOne
	@JoinColumn(name = "fk_exam_id", nullable = false)
	private Exam exam;
	
	@ManyToOne
	@JoinColumn(name = "fk_question_id", nullable = false)
	private Question question;
}