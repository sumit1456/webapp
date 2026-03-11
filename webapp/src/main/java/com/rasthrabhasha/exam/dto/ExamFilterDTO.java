package com.rasthrabhasha.exam.dto;

public class ExamFilterDTO {
    private String examName;
    private String examCode;
    private String status;

    // Getters and Setters
    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getExamCode() {
        return examCode;
    }

    public void setExamCode(String examCode) {
        this.examCode = examCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamFilterDTO that = (ExamFilterDTO) o;
        return java.util.Objects.equals(examName, that.examName) &&
               java.util.Objects.equals(examCode, that.examCode) &&
               java.util.Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(examName, examCode, status);
    }
}
