package com.rasthrabhasha.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

	
	@org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)
	public String handleEntityNotFound(EntityNotFoundException enf) {
		return enf.getMessage();
	}
}
