package com.rasthrabhasha.analytics.dto;

import java.util.Map;

public class DistributionDTO {
    private Map<String, Long> studentsByRegion;
    private Map<String, Long> schoolsByRegion;

    public DistributionDTO() {}

    public DistributionDTO(Map<String, Long> studentsByRegion, Map<String, Long> schoolsByRegion) {
        this.studentsByRegion = studentsByRegion;
        this.schoolsByRegion = schoolsByRegion;
    }

    public Map<String, Long> getStudentsByRegion() {
        return studentsByRegion;
    }

    public void setStudentsByRegion(Map<String, Long> studentsByRegion) {
        this.studentsByRegion = studentsByRegion;
    }

    public Map<String, Long> getSchoolsByRegion() {
        return schoolsByRegion;
    }

    public void setSchoolsByRegion(Map<String, Long> schoolsByRegion) {
        this.schoolsByRegion = schoolsByRegion;
    }
}
