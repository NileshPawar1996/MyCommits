package com.app.dtos.module;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusAndTeacherNameDto {
	private Long moduleId;
	private Boolean isCompleted;
	private String teacherName;
	private String moduleName;
}
