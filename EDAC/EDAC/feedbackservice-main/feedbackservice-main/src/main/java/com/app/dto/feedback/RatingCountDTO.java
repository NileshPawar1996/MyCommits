package com.app.dto.feedback;

import com.app.pojos.Feedback;
import com.app.pojos.FeedbackCriteria;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RatingCountDTO {
	private Feedback feedback;
	private FeedbackCriteria criteria;
	private Long count;
	private Byte rating;
	
	public RatingCountDTO(Feedback feedback, FeedbackCriteria criteria, Long count, Byte rating) {
		this.feedback = feedback;
		this.criteria = criteria;
		this.count = count;
		this.rating = rating;
	}
}
