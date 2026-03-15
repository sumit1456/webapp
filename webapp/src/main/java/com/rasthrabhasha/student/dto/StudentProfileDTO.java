package com.rasthrabhasha.student.dto;

import java.time.LocalDate;
import com.rasthrabhasha.common.Address;

public class StudentProfileDTO {
    private Long profileId;
    private LocalDate dateOfBirth;
    private String gender;
    private String category;
    private String previousExamName;
    private Double previousExamMarks;
    private String previousExamRollNO;
    private Integer previousExamYear;
    private String fatherName;
    private String motherName;
    private String guardianContact;
    private String qualification;
    private String profilePhotoUrl;
    private String signatureUrl;
    private String idProofNumber;
    private String idProofDocumentUrl;
    private String profileCompletionStatus;
    private Address address;
    private Long studentId;

    public String getPreviousExamRollNO() {
		return previousExamRollNO;
	}

	public void setPreviousExamRollNO(String previousExamRollNO) {
		this.previousExamRollNO = previousExamRollNO;
	}

	public Integer getPreviousExamYear() {
		return previousExamYear;
	}

	public void setPreviousExamYear(Integer previousExamYear) {
		this.previousExamYear = previousExamYear;
	}

	public StudentProfileDTO() {}

    public StudentProfileDTO(Long profileId, LocalDate dateOfBirth, String gender, String category,
            String previousExamName, Double previousExamMarks, String previousExamRollNO, Integer previousExamYear,  String fatherName, String motherName, String guardianContact, String qualification, String profilePhotoUrl, String signatureUrl,
            String idProofNumber, String idProofDocumentUrl,
            String profileCompletionStatus, Address address, Long studentId) {
        this.profileId = profileId;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.category = category;
        this.previousExamName = previousExamName;
        this.previousExamMarks = previousExamMarks;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.guardianContact = guardianContact;
        this.qualification = qualification;
        this.profilePhotoUrl = profilePhotoUrl;
        this.signatureUrl = signatureUrl;
        this.idProofNumber = idProofNumber;
        this.idProofDocumentUrl = idProofDocumentUrl;
        this.profileCompletionStatus = profileCompletionStatus;
        this.address = address;
        this.studentId = studentId;
        this.previousExamRollNO = previousExamRollNO;
        this.previousExamYear = previousExamYear;
        
    }

    public Long getProfileId() { return profileId; }
    public void setProfileId(Long profileId) { this.profileId = profileId; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getPreviousExamName() { return previousExamName; }
    public void setPreviousExamName(String previousExamName) { this.previousExamName = previousExamName; }
    public Double getPreviousExamMarks() { return previousExamMarks; }
    public void setPreviousExamMarks(Double previousExamMarks) { this.previousExamMarks = previousExamMarks; }
    public String getFatherName() { return fatherName; }
    public void setFatherName(String fatherName) { this.fatherName = fatherName; }
    public String getMotherName() { return motherName; }
    public void setMotherName(String motherName) { this.motherName = motherName; }
    public String getGuardianContact() { return guardianContact; }
    public void setGuardianContact(String guardianContact) { this.guardianContact = guardianContact; }
    public String getQualification() { return qualification; }
    public void setQualification(String qualification) { this.qualification = qualification; }
    public String getProfilePhotoUrl() { return profilePhotoUrl; }
    public void setProfilePhotoUrl(String profilePhotoUrl) { this.profilePhotoUrl = profilePhotoUrl; }
    public String getSignatureUrl() { return signatureUrl; }
    public void setSignatureUrl(String signatureUrl) { this.signatureUrl = signatureUrl; }
    public String getIdProofNumber() { return idProofNumber; }
    public void setIdProofNumber(String idProofNumber) { this.idProofNumber = idProofNumber; }
    public String getIdProofDocumentUrl() { return idProofDocumentUrl; }
    public void setIdProofDocumentUrl(String idProofDocumentUrl) { this.idProofDocumentUrl = idProofDocumentUrl; }
    public String getProfileCompletionStatus() { return profileCompletionStatus; }
    public void setProfileCompletionStatus(String profileCompletionStatus) { this.profileCompletionStatus = profileCompletionStatus; }
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
}
