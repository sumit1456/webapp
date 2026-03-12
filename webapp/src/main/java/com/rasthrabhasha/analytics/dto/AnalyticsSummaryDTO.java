package com.rasthrabhasha.analytics.dto;

public class AnalyticsSummaryDTO {
    private long totalRegions;
    private long totalCentres;
    private long totalSchools;
    private long totalStudents;

    public AnalyticsSummaryDTO() {}

    public AnalyticsSummaryDTO(long totalRegions, long totalCentres, long totalSchools, long totalStudents) {
        this.totalRegions = totalRegions;
        this.totalCentres = totalCentres;
        this.totalSchools = totalSchools;
        this.totalStudents = totalStudents;
    }

    public long getTotalRegions() {
        return totalRegions;
    }

    public void setTotalRegions(long totalRegions) {
        this.totalRegions = totalRegions;
    }

    public long getTotalCentres() {
        return totalCentres;
    }

    public void setTotalCentres(long totalCentres) {
        this.totalCentres = totalCentres;
    }

    public long getTotalSchools() {
        return totalSchools;
    }

    public void setTotalSchools(long totalSchools) {
        this.totalSchools = totalSchools;
    }

    public long getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(long totalStudents) {
        this.totalStudents = totalStudents;
    }
}
