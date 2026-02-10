package com.rasthrabhasha.school;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rasthrabhasha.examcentre.ExamCentre;
import com.rasthrabhasha.student.Student;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "school")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class School {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long schoolId;

	@Column(nullable = false)
	private String schoolName;

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public ExamCentre getExamCentre() {
		return examCentre;
	}

	public void setExamCentre(ExamCentre examCentre) {
		this.examCentre = examCentre;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "centre_id", nullable = false)
	@JsonIgnoreProperties({ "schools", "hibernateLazyInitializer", "handler" })
	private ExamCentre examCentre;

	@OneToMany(mappedBy = "school")
	@JsonIgnoreProperties("school")
	@JsonIgnore
	private List<Student> students;

}
