package com.app.dtos.session;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessionsForAttendanceCalculationsDto {
	private Long moduleId;
	private String moduleName;
	private List<GetAllSessionsAndAllModulesByModuleIdDto> listOfSessions = new ArrayList<>();
}
