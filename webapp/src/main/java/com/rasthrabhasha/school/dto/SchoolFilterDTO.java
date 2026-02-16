package com.rasthrabhasha.school.dto;

public class SchoolFilterDTO {
    private String schoolName;
    private Long examCentreId;
    private Long regionId;

    // Getters and Setters
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Long getExamCentreId() {
        return examCentreId;
    }

    public void setExamCentreId(Long examCentreId) {
        this.examCentreId = examCentreId;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }
}
