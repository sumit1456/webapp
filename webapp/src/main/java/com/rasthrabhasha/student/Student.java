package com.rasthrabhasha.student;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rasthrabhasha.application.ExamApplication;
import com.rasthrabhasha.school.School;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Student {

	
	//Student Entity
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
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "school_id", nullable = false)
	@JsonIgnoreProperties("students")
	private School school;

	


	

	public School getSchool() {
		return school;
	}


	public void setSchool(School school) {
		this.school = school;
	}


	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}


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

