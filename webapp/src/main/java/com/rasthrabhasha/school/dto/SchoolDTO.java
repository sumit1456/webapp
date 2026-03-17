package com.rasthrabhasha.school.dto;

import com.rasthrabhasha.common.Address;

public class SchoolDTO {
    private Long schoolId;
    private String schoolName;
    private String schoolCode;
    private Long centreId;
    private String centreName;
    private String boardAffiliation;
    private String mediumOfInstruction;
    private Integer establishmentYear;
    private String principalName;
    private String principalContactNumber;
    private String secondaryContactNumber;
    private String officialEmail;
    private String websiteUrl;
    private Integer seatingCapacity;
    private Integer numberOfClassrooms;
    private Boolean cctvAvailable;
    private Address address;
    private String principalSignatureUrl;
    private String schoolStampUrl;

    public SchoolDTO() {
    }

    public SchoolDTO(Long schoolId, String schoolName, Long centreId, String centreName) {
        this.schoolId = schoolId;
        this.schoolName = schoolName;
        this.centreId = centreId;
        this.centreName = centreName;
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

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public Long getCentreId() {
        return centreId;
    }

    public void setCentreId(Long centreId) {
        this.centreId = centreId;
    }

    public String getCentreName() {
        return centreName;
    }

    public void setCentreName(String centreName) {
        this.centreName = centreName;
    }

    public String getBoardAffiliation() {
        return boardAffiliation;
    }

    public void setBoardAffiliation(String boardAffiliation) {
        this.boardAffiliation = boardAffiliation;
    }

    public String getMediumOfInstruction() {
        return mediumOfInstruction;
    }

    public void setMediumOfInstruction(String mediumOfInstruction) {
        this.mediumOfInstruction = mediumOfInstruction;
    }

    public Integer getEstablishmentYear() {
        return establishmentYear;
    }

    public void setEstablishmentYear(Integer establishmentYear) {
        this.establishmentYear = establishmentYear;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public String getPrincipalContactNumber() {
        return principalContactNumber;
    }

    public void setPrincipalContactNumber(String principalContactNumber) {
        this.principalContactNumber = principalContactNumber;
    }

    public String getSecondaryContactNumber() {
        return secondaryContactNumber;
    }

    public void setSecondaryContactNumber(String secondaryContactNumber) {
        this.secondaryContactNumber = secondaryContactNumber;
    }

    public String getOfficialEmail() {
        return officialEmail;
    }

    public void setOfficialEmail(String officialEmail) {
        this.officialEmail = officialEmail;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public Integer getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(Integer seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public Integer getNumberOfClassrooms() {
        return numberOfClassrooms;
    }

    public void setNumberOfClassrooms(Integer numberOfClassrooms) {
        this.numberOfClassrooms = numberOfClassrooms;
    }

    public Boolean getCctvAvailable() {
        return cctvAvailable;
    }

    public void setCctvAvailable(Boolean cctvAvailable) {
        this.cctvAvailable = cctvAvailable;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPrincipalSignatureUrl() {
        return principalSignatureUrl;
    }

    public void setPrincipalSignatureUrl(String principalSignatureUrl) {
        this.principalSignatureUrl = principalSignatureUrl;
    }

    public String getSchoolStampUrl() {
        return schoolStampUrl;
    }

    public void setSchoolStampUrl(String schoolStampUrl) {
        this.schoolStampUrl = schoolStampUrl;
    }
}
