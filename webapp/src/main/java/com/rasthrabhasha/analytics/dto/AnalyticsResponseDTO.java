package com.rasthrabhasha.analytics.dto;

import java.util.List;

public class AnalyticsResponseDTO {
    private AnalyticsSummaryDTO summary;
    private DistributionDTO distribution;
    private List<SchoolStudentCountDTO> topSchools;

    public AnalyticsResponseDTO() {}

    public AnalyticsResponseDTO(AnalyticsSummaryDTO summary, DistributionDTO distribution, List<SchoolStudentCountDTO> topSchools) {
        this.summary = summary;
        this.distribution = distribution;
        this.topSchools = topSchools;
    }

    public AnalyticsSummaryDTO getSummary() {
        return summary;
    }

    public void setSummary(AnalyticsSummaryDTO summary) {
        this.summary = summary;
    }

    public DistributionDTO getDistribution() {
        return distribution;
    }

    public void setDistribution(DistributionDTO distribution) {
        this.distribution = distribution;
    }

    public List<SchoolStudentCountDTO> getTopSchools() {
        return topSchools;
    }

    public void setTopSchools(List<SchoolStudentCountDTO> topSchools) {
        this.topSchools = topSchools;
    }
}
