package com.rasthrabhasha.dto;

public class ExamCentreDTO {
    private Long centreId;
    private String centreCode;
    private String centreName;
    private Long regionId;
    private String regionName;

    public ExamCentreDTO() {
    }

    public ExamCentreDTO(Long centreId, String centreCode, String centreName, Long regionId, String regionName) {
        this.centreId = centreId;
        this.centreCode = centreCode;
        this.centreName = centreName;
        this.regionId = regionId;
        this.regionName = regionName;
    }

    public Long getCentreId() {
        return centreId;
    }

    public void setCentreId(Long centreId) {
        this.centreId = centreId;
    }

    public String getCentreCode() {
        return centreCode;
    }

    public void setCentreCode(String centreCode) {
        this.centreCode = centreCode;
    }

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

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}
