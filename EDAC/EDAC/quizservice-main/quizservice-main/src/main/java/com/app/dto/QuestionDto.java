package com.app.dto;

import com.app.pojos.Topic;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class QuestionDto {
	private Long id ;
	private String question;
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
	
	public QuestionDto(Long id, String question, String optionA, String optionB, String optionC, String optionD){
		this.id = id;
		this.question = question;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.optionD = optionD;
	}
	
	
}
