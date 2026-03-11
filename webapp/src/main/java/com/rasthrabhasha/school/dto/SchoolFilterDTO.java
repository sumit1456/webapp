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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchoolFilterDTO that = (SchoolFilterDTO) o;
        return java.util.Objects.equals(schoolName, that.schoolName) &&
               java.util.Objects.equals(examCentreId, that.examCentreId) &&
               java.util.Objects.equals(regionId, that.regionId);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(schoolName, examCentreId, regionId);
    }
}
