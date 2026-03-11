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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamCentreFilterDTO that = (ExamCentreFilterDTO) o;
        return java.util.Objects.equals(centreName, that.centreName) &&
               java.util.Objects.equals(regionId, that.regionId) &&
               java.util.Objects.equals(centreCode, that.centreCode);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(centreName, regionId, centreCode);
    }
}
