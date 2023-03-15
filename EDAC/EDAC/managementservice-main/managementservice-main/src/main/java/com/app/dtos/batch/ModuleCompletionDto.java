package com.app.dtos.batch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModuleCompletionDto {
	private Long id;
	private String moduleName;
	private Boolean isCompleted;
	private String teacherName;
}
