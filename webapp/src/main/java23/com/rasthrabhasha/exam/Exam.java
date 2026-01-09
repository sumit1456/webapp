package com.rasthrabhasha.exam;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rasthrabhasha.application.ExamApplication;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;



@Entity
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long examNo;

    private String exam_name;

    private int no_of_papers;

    private double exam_fees;

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
		return "Exam [exam_name=" + exam_name + ", no_of_papers=" + no_of_papers + ", exam_fees=" + exam_fees + "]";
	}

    // getters & setters
}
