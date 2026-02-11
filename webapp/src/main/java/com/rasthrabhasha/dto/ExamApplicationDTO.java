package com.rasthrabhasha.dto;

public class ExamApplicationDTO {
    private Long applicationId;
    private long examNo;
    private String examName;
    private long studentId;
    private String studentName;
    private String status;

    public ExamApplicationDTO() {
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public long getExamNo() {
        return examNo;
    }

    public void setExamNo(long examNo) {
        this.examNo = examNo;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

   
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
