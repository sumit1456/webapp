package com.rasthrabhasha.student;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rasthrabhasha.common.Address;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "student_profile")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    @Column
    private LocalDate dateOfBirth;

    @Column
    private String gender;

    @Column
    private String category;

    @Column
    private String previousExamName;
    
    @Column
    private Integer previousExamYear;
    
    
    public Integer getPreviousExamYear() {
		return previousExamYear;
	}

	public void setPreviousExamYear(Integer previousExamYear) {
		this.previousExamYear = previousExamYear;
	}

	public String getPreviousExamRollNO() {
		return previousExamRollNO;
	}

	public void setPreviousExamRollNO(String previousExamRollNO) {
		this.previousExamRollNO = previousExamRollNO;
	}

	@Column
    String previousExamRollNO;

    @Column
    private Double previousExamMarks;

    @Column
    private String fatherName;

    @Column
    private String motherName;

    @Column
    private String guardianContact;

    @Column
    private String qualification;

    


    @Column(name = "profile_photo_url", columnDefinition = "TEXT")
    private String profilePhotoUrl;

    @Column(name = "signature_url", columnDefinition = "TEXT")
    private String signatureUrl;

    @Column(name = "id_proof_document_url", columnDefinition = "TEXT")
    private String idProofDocumentUrl;

   
    @Column
    private String idProofNumber;

 

    @Column
    private String profileCompletionStatus;

    @Embedded
    private Address address;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Student student;

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPreviousExamName() {
        return previousExamName;
    }

    public void setPreviousExamName(String previousExamName) {
        this.previousExamName = previousExamName;
    }

    public Double getPreviousExamMarks() {
        return previousExamMarks;
    }

    public void setPreviousExamMarks(Double previousExamMarks) {
        this.previousExamMarks = previousExamMarks;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getGuardianContact() {
        return guardianContact;
    }

    public void setGuardianContact(String guardianContact) {
        this.guardianContact = guardianContact;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public String getSignatureUrl() {
        return signatureUrl;
    }

    public void setSignatureUrl(String signatureUrl) {
        this.signatureUrl = signatureUrl;
    }

    public String getIdProofNumber() {
        return idProofNumber;
    }

    public void setIdProofNumber(String idProofNumber) {
        this.idProofNumber = idProofNumber;
    }

    public String getIdProofDocumentUrl() {
        return idProofDocumentUrl;
    }

    public void setIdProofDocumentUrl(String idProofDocumentUrl) {
        this.idProofDocumentUrl = idProofDocumentUrl;
    }

    public String getProfileCompletionStatus() {
        return profileCompletionStatus;
    }

    public void setProfileCompletionStatus(String profileCompletionStatus) {
        this.profileCompletionStatus = profileCompletionStatus;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}
