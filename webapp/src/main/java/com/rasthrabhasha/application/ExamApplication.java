package com.rasthrabhasha.application;

import com.rasthrabhasha.exam.Exam;
import com.rasthrabhasha.student.Student;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ExamApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;


    @ManyToOne
    @JoinColumn(name = "exam_no")
    private Exam exam;

    private String rollNo;

    private Long centreId;

    @Column(name = "is_hall_ticket_generated", columnDefinition = "boolean default false")
    private Boolean isHallTicketGenerated = false;
    
    
    public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getFormData() {
		return formData;
	}

	public void setFormData(String formData) {
		this.formData = formData;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public Long getCentreId() {
		return centreId;
	}

	public void setCentreId(Long centreId) {
		this.centreId = centreId;
	}

	public Boolean getIsHallTicketGenerated() {
		return isHallTicketGenerated;
	}

	public void setIsHallTicketGenerated(Boolean isHallTicketGenerated) {
		this.isHallTicketGenerated = isHallTicketGenerated;
	}

	@ManyToOne
    private Student student;

    @Column(columnDefinition = "json")
    private String formData;   // exam-specific fields

    private String status;     // SUBMITTED / APPROVED / REJECTED
}

