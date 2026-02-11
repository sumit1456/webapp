package com.rasthrabhasha.dto;

public class RegionDTO {
    private Long regionId;
    private String regionName;
    
    private int no_exam_centres;

    public int getNo_exam_centres() {
		return no_exam_centres;
	}

	public void setNo_exam_centres(int no_exam_centres) {
		this.no_exam_centres = no_exam_centres;
	}

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
