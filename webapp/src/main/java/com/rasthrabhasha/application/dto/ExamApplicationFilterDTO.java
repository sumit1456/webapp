package com.rasthrabhasha.application.dto;

public class ExamApplicationFilterDTO {

	// filter by exam (ManyToOne Exam)
	private Long examId;

	// filter by student (ManyToOne Student)
	private Long studentId;

	// filter by status (SUBMITTED / APPROVED / REJECTED)
	private String status;

	private Long regionId;

	private Long SchoolId;

	private Long examCentre;

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public Long getSchoolId() {
		return SchoolId;
	}

	public void setSchoolId(Long schoolId) {
		SchoolId = schoolId;
	}

	public Long getExamCentre() {
		return examCentre;
	}

	public void setExamCentre(Long examCentre) {
		this.examCentre = examCentre;
	}

	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	// optional: filter by application id
	private Long applicationId;

	// getters & setters
}
