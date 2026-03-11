package com.rasthrabhasha.result.dto;

public class ExamResultFilterDTO {
    private Long studentId;
    private Long examId;
    private Long schoolId;
    private Long regionId;
    private Double minPercentage;
    private Double maxPercentage;

    // Getters and Setters
    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public Double getMinPercentage() {
        return minPercentage;
    }

    public void setMinPercentage(Double minPercentage) {
        this.minPercentage = minPercentage;
    }

    public Double getMaxPercentage() {
        return maxPercentage;
    }

    public void setMaxPercentage(Double maxPercentage) {
        this.maxPercentage = maxPercentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamResultFilterDTO that = (ExamResultFilterDTO) o;
        return java.util.Objects.equals(studentId, that.studentId) &&
               java.util.Objects.equals(examId, that.examId) &&
               java.util.Objects.equals(schoolId, that.schoolId) &&
               java.util.Objects.equals(regionId, that.regionId) &&
               java.util.Objects.equals(minPercentage, that.minPercentage) &&
               java.util.Objects.equals(maxPercentage, that.maxPercentage);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(studentId, examId, schoolId, regionId, minPercentage, maxPercentage);
    }
}
