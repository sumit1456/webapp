package com.rasthrabhasha.student;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rasthrabhasha.application.ExamApplication;
import com.rasthrabhasha.exam.Exam;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Student {

	
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long studentId;
	

	@Column
	private String username;
	
	
	@Column
	private String password;
	
	
	
	@OneToMany(mappedBy = "student")
	@JsonIgnore
    private List<ExamApplication> applications;

	


	

	public List<ExamApplication> getApplications() {
		return applications;
	}


	public void setApplications(List<ExamApplication> applications) {
		this.applications = applications;
	}


	public long getStudentId() {
		return studentId;
	}


	public void setStudent_id(long studentId) {
		this.studentId = studentId;
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
}

