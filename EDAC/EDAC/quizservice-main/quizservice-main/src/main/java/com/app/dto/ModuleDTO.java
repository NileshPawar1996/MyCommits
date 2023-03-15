package com.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ModuleDTO {
	private Long id;
	private Object course;
	private String moduleName;
	private int durationHrs;
}
