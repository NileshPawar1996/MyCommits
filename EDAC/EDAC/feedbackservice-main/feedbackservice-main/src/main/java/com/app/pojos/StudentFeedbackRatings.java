package com.app.pojos;
//by AdityaS
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.app.dto.feedback.StudentFeedbackSubmittedDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name = "student_feedback_ratings")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
//@NamedNativeQuery(name = "StudentFeedbackRatingsDao.countRatingsByFeedbackIds",
//query = "select feedback, criteria, count(rating) as count, rating from student_feedback_ratings where feedback in :feedbackIds group by rating, criteria order by criteria, rating desc",
//resultSetMapping = "Mapping.RatingCountDTO")
//@SqlResultSetMapping(name = "Mapping.RatingCountDTO",
//classes = @ConstructorResult(targetClass = StudentFeedbackSubmittedDTO.class,
//                             columns = {@ColumnResult(name = "feedback"),
//                                        @ColumnResult(name = "criteria"),
//                                        @ColumnResult(name = "count"),
//                                        @ColumnResult(name = "rating")}))
public class StudentFeedbackRatings {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_feedback_rating_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "feedback")
	private Feedback feedback;
	
	@ManyToOne
	@JoinColumn(name = "criteria" )
	private FeedbackCriteria criteria;
	
	@Column(name = "rating")
	private Byte rating;
}
