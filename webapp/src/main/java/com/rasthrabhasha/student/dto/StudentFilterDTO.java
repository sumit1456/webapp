package com.rasthrabhasha.student.dto;

public class StudentFilterDTO {
    private String firstName;
    private String lastName;
    private Long schoolId;
    private String email;
    private Long studentId;

    public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	// Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentFilterDTO that = (StudentFilterDTO) o;
        return java.util.Objects.equals(firstName, that.firstName) &&
               java.util.Objects.equals(lastName, that.lastName) &&
               java.util.Objects.equals(schoolId, that.schoolId) &&
               java.util.Objects.equals(email, that.email) &&
               java.util.Objects.equals(studentId, that.studentId);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(firstName, lastName, schoolId, email, studentId);
    }
}
