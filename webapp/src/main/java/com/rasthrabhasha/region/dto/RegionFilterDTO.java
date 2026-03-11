package com.rasthrabhasha.region.dto;

import java.util.Objects;

public class RegionFilterDTO {

    private String regionName;

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegionFilterDTO)) return false;
        RegionFilterDTO that = (RegionFilterDTO) o;
        return Objects.equals(regionName, that.regionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regionName);
    }
}