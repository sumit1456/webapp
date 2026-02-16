package com.rasthrabhasha.admin.dto;

public class DashboardStatsDTO {
	private long studentCount;
	private long examCount;
	private long applicationCount;
	private long resultCount;

	public long getStudentCount() {
		return studentCount;
	}

	public void setStudentCount(long studentCount) {
		this.studentCount = studentCount;
	}

	public long getExamCount() {
		return examCount;
	}

	public void setExamCount(long examCount) {
		this.examCount = examCount;
	}

	public long getApplicationCount() {
		return applicationCount;
	}

	public void setApplicationCount(long applicationCount) {
		this.applicationCount = applicationCount;
	}

	public long getResultCount() {
		return resultCount;
	}

	public void setResultCount(long resultCount) {
		this.resultCount = resultCount;
	}

}