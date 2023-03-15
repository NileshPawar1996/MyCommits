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
public class GetAllSessionsAndAllModulesByModuleIdDto {
	private Long id;
	private long sessionDuration;
}
