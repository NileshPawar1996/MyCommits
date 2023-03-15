package com.app.dtos.session;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionInDescOrderDto {
	private Long id;
	private String batch;
	private String courseName;
	private String module;
	private String staffName;
	private LocalDate date;
	private LocalTime startTime;
	private LocalTime endTime;
	
}
