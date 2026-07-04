package com.rasthrabhasha.examofficer.dto;

public class ExamOfficerDTO {
	private long examOfficerId;
	private String username;
	private String password;
	private String name;

	public ExamOfficerDTO() {
	}

	public ExamOfficerDTO(long examOfficerId, String username, String name) {
		this.examOfficerId = examOfficerId;
		this.username = username;
		this.name = name;
	}

	public long getExamOfficerId() {
		return examOfficerId;
	}

	public void setExamOfficerId(long examOfficerId) {
		this.examOfficerId = examOfficerId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
