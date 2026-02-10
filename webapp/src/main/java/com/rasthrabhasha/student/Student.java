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
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Student {

	// Student Entity
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long studentId;

	@Column
	private String firstName;

	@Column
	private String middleName;

	@Column
	private String lastName;

	@Column
	private String contact;

	@Column
	private String email;

	@Column
	private String password;

	@Column
	private Integer age;

	@Column
	private String motherTongue;

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

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getMotherTongue() {
		return motherTongue;
	}

	public void setMotherTongue(String motherTongue) {
		this.motherTongue = motherTongue;
	}
}
