package com.rasthrabhasha.dto;

public class RegionDTO {
    private Long regionId;
    private String regionName;

    public RegionDTO() {
    }

    public RegionDTO(Long regionId, String regionName) {
        this.regionId = regionId;
        this.regionName = regionName;
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
