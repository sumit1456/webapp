package com.rasthrabhasha.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

	
	@org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)
	public org.springframework.http.ResponseEntity<String> handleEntityNotFound(EntityNotFoundException ex) {
		return org.springframework.http.ResponseEntity.status(org.springframework.http.HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
	public org.springframework.http.ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
		return org.springframework.http.ResponseEntity.badRequest().body(ex.getMessage());
	}
}
