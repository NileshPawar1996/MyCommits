package com.sunbeam.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

	@NotNull(message = "First Name cannot be blank")
	@Length(max = 20)
	@Column(name = "first_name", length = 20)
	private String firstName;

	@NotNull(message = "Last Name cannot be blank")
	@Length(max = 25)
	@Column(name = "last_name", length = 20)
	private String lastName;

	@NotNull(message = "Email cannot be blank")
	@Length(max = 30)
	@Email(message = "Invalid email format!")
	@Column(length = 30, unique = true, nullable = false)
	private String email;

	@NotNull(message = "Password cannot be null")
	@Length(max = 20)
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", message = "Invalid Password !")
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(length = 30, nullable = false)
	private String password;

}