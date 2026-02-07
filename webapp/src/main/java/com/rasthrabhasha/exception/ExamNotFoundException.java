package com.rasthrabhasha.exception;

public class ExamNotFoundException extends Exception {

	ExamNotFoundException(String msg){
		super(msg);
	}
	
	@Override
	public String getMessage() {
		
		return "Invalid exam no";
	}
}
