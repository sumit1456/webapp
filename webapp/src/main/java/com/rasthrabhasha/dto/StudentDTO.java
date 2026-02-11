package com.rasthrabhasha.dto;

import java.util.List;
import com.rasthrabhasha.application.ExamApplication;

public class StudentDTO {
    private long studentId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String contact;
    private String email;
    private Integer age;
    private String motherTongue;
    private Long schoolId;
    private String schoolName;
    
    private List<ExamApplication> examApplications;

    public StudentDTO() {
    }

    public StudentDTO(long studentId, String firstName, String middleName, String lastName, String contact,
            String email, Integer age, String motherTongue, Long schoolId, String schoolName) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.contact = contact;
        this.email = email;
        this.age = age;
        this.motherTongue = motherTongue;
        this.schoolId = schoolId;
        this.schoolName = schoolName;
    }

    public List<ExamApplication> getExamApplications() {
		return examApplications;
	}

	public void setExamApplications(List<ExamApplication> examApplications) {
		this.examApplications = examApplications;
	}

	public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMotherTongue() {
        return motherTongue;
    }

    public void setMotherTongue(String motherTongue) {
        this.motherTongue = motherTongue;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
