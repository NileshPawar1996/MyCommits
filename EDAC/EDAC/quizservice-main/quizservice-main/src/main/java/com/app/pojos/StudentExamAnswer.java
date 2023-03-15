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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="student_exam_answers")
@Getter
@Setter
@NoArgsConstructor
public class StudentExamAnswer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_exam_answer_id")
	private Long id;
	@Column(name = "fk_student_id")
	private Long studentId;
	@ManyToOne
	@JoinColumn(name = "fk_exam_id", nullable = false)
	private Exam exam;
	@ManyToOne
	@JoinColumn(name = "fk_question_id", nullable = false)
	private Question question;
	@Column(name = "selected_answer")
	private Character selectedAnswer;
}
