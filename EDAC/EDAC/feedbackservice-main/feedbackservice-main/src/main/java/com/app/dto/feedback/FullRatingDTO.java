package com.app.dto.feedback;

import java.util.ArrayList;
import java.util.List;

import com.app.pojos.Feedback;
import com.app.pojos.StudentFeedbackRatings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FullRatingDTO {
	Feedback studentFeedback;
	List<StudentFeedbackRatings> criteriaRating = new ArrayList<>();
}
