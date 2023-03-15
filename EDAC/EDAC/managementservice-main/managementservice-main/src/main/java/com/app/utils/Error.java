package com.app.utils;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Error {
	private String errorType;
	private String message;
	private HttpStatus statusCode;
	private LocalDateTime dateTime;
}
