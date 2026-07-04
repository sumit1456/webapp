package com.rasthrabhasha.examofficer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "exam_officer")
public class ExamOfficer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long examOfficerId;

	@Column(unique = true, nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column
	private String name;

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
