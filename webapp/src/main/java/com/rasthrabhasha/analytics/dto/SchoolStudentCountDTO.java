package com.rasthrabhasha.analytics.dto;

public class SchoolStudentCountDTO {
    private String schoolName;
    private long studentCount;

    public SchoolStudentCountDTO() {}

    public SchoolStudentCountDTO(String schoolName, long studentCount) {
        this.schoolName = schoolName;
        this.studentCount = studentCount;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public long getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(long studentCount) {
        this.studentCount = studentCount;
    }
}
