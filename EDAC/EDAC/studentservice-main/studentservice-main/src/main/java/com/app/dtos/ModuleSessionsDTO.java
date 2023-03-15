package com.app.dtos;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class ModuleSessionsDTO {
	Long moduleId;
	String moduleName;
	List<SessionDTO> listOfSessions;
	
}
