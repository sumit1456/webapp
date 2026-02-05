package com.rasthrabhasha.exam;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rasthrabhasha.application.ExamApplication;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Column;

@Entity
public class Exam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long examNo;

	private String exam_name;

	private int no_of_papers;

	private double exam_fees;

	@Column(columnDefinition = "json")
	private String papers; // Stores JSON: [{"name": "Paper 1", "maxMarks": 100}, ...]
	
	@Column(columnDefinition = "json")
	private String exam_details; // To store {"center": "...", "session": "...", "description": "..."}

	public String getExam_details() {
		return exam_details;
	}

	public void setExam_details(String exam_details) {
		this.exam_details = exam_details;
	}

	public void setExamNo(long examNo) {
		this.examNo = examNo;
	}

	public long getExamNo() {
		return examNo;
	}

	public void setExam_no(long exam_n0) {
		this.examNo = exam_n0;
	}

	public String getExam_name() {
		return exam_name;
	}

	public void setExam_name(String exam_name) {
		this.exam_name = exam_name;
	}

	public int getNo_of_papers() {
		return no_of_papers;
	}

	public void setNo_of_papers(int no_of_papers) {
		this.no_of_papers = no_of_papers;
	}

	public double getExam_fees() {
		return exam_fees;
	}

	public void setExam_fees(double exam_fees) {
		this.exam_fees = exam_fees;
	}

	public String getPapers() {
		return papers;
	}

	public void setPapers(String papers) {
		this.papers = papers;
	}

	public List<ExamApplication> getApplications() {
		return applications;
	}

	public void setApplications(List<ExamApplication> applications) {
		this.applications = applications;
	}

	@OneToMany(mappedBy = "exam")
	@JsonIgnore
	private List<ExamApplication> applications;

	@Override
	public String toString() {
		return "Exam [exam_name=" + exam_name + ", no_of_papers=" + no_of_papers + ", exam_fees=" + exam_fees
				+ ", papers=" + papers + "]";
	}

	// getters & setters
}
