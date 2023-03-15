// //created by Aditya Shinde
package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContactDetails {
	@Column(name = "city", length = 30)
	private String city;
	@Column(name = "phone_no", length = 10)
	private String phoneNo; 
	@Column(name = "pincode", length = 6)
	private String pincode;
	@Column(name = "state", length = 30)
	private String state;
	@Column(name = "country", length = 30)
	private String country;
}
