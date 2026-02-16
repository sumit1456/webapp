package com.rasthrabhasha.examcentre.dto;

public class ExamCentreFilterDTO {
    private String centreName;
    private Long regionId;
    private String centreCode;

    // Getters and Setters
    public String getCentreName() {
        return centreName;
    }

    public void setCentreName(String centreName) {
        this.centreName = centreName;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getCentreCode() {
        return centreCode;
    }

    public void setCentreCode(String centreCode) {
        this.centreCode = centreCode;
    }
}
