package com.rasthrabhasha.dto;

public class SchoolDTO {
    private Long schoolId;
    private String schoolName;
    private Long centreId;
    private String centreName;

    public SchoolDTO() {
    }

    public SchoolDTO(Long schoolId, String schoolName, Long centreId, String centreName) {
        this.schoolId = schoolId;
        this.schoolName = schoolName;
        this.centreId = centreId;
        this.centreName = centreName;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Long getCentreId() {
        return centreId;
    }

    public void setCentreId(Long centreId) {
        this.centreId = centreId;
    }

    public String getCentreName() {
        return centreName;
    }

    public void setCentreName(String centreName) {
        this.centreName = centreName;
    }
}
