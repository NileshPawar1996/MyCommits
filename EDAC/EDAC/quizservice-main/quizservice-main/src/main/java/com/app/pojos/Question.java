//CREATED BY RAJVARDHAN SHINDE
package com.app.pojos;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table (name = "questions")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_id")
	private Long id ;
	
	@Column(name = "fk_module_id", nullable = false)
	private Long moduleId;
	
	@ManyToOne
	@JoinColumn(name = "fk_topic_id", nullable = false)
	private Topic topic;
	
	@Column
	private String question;
	
	
	@Column(name = "option_a" )
	private String optionA;
	
	
	@Column(name = "option_b")
	private String optionB;
	
	
	@Column(name = "option_c")
	private String optionC;
	
	
	@Column(name = "option_d")
	private String optionD;
	
	
	@Column
	private Character answer;
	
	
	@Column(name = "answer_description")
	private String answerDesc;
	
	
	@Column(name = "creation_date_time")
	@CreationTimestamp
	private LocalDateTime creationTime;
	
	
}
