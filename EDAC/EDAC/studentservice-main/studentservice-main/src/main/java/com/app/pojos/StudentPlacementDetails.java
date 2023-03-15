//created by : Rajvardhan Shinde
package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable 
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudentPlacementDetails {
	@Column(name = "fk_company_id", nullable = true)
	private Long companyId;
	
	@Column(length = 30)
	private String location;
	
	@Column(name ="ctc", precision = 2)
	private Double CTC;
	
	@Enumerated(EnumType.STRING)
	private Designation designation; 
}
