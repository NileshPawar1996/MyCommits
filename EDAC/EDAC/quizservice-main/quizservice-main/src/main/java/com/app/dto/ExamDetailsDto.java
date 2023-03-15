package com.app.dto;

import java.time.LocalDateTime;
import java.util.Objects;

import com.app.pojos.ExamType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ExamDetailsDto {
	private Long examId;
	private int totalQuestions;
	private int correctQuestions;
	private int percentage;
	private Integer maxMarks;
	private LocalDateTime endDateTime;
	private LocalDateTime startDateTime;
	private ExamType type;
	private ModuleDTO module;
	private Long batchId;
	@Override
	public int hashCode() {
		return Objects.hash(examId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExamDetailsDto other = (ExamDetailsDto) obj;
		return examId == other.examId;
	}
	
}
