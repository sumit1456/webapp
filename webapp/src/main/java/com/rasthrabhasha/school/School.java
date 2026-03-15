package com.rasthrabhasha.school;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rasthrabhasha.examcentre.ExamCentre;
import com.rasthrabhasha.student.Student;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "school", indexes = {
		@jakarta.persistence.Index(name = "idx_school_centre_id", columnList = "centre_id")
})
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class School {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long schoolId;

	@Column(nullable = false)
	private String schoolName;

	@Column(name = "school_code")
	private String schoolCode;

	@Column(name = "board_affiliation")
	private String boardAffiliation;

	@Column(name = "medium_of_instruction")
	private String mediumOfInstruction;

	@Column(name = "establishment_year")
	private Integer establishmentYear;

	@Column(name = "principal_name")
	private String principalName;

	@Column(name = "principal_contact_number")
	private String principalContactNumber;

	@Column(name = "secondary_contact_number")
	private String secondaryContactNumber;

	@Column(name = "official_email")
	private String officialEmail;

	@Column(name = "website_url")
	private String websiteUrl;

	@Column(name = "seating_capacity")
	private Integer seatingCapacity;

	@Column(name = "number_of_classrooms")
	private Integer numberOfClassrooms;

	@Column(name = "cctv_available")
	private Boolean cctvAvailable;

	@jakarta.persistence.Embedded
	private com.rasthrabhasha.common.Address address;

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

	public com.rasthrabhasha.common.Address getAddress() {
		return address;
	}

	public void setAddress(com.rasthrabhasha.common.Address address) {
		this.address = address;
	}

	public ExamCentre getExamCentre() {
		return examCentre;
	}

	public void setExamCentre(ExamCentre examCentre) {
		this.examCentre = examCentre;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "centre_id", nullable = false)
	@JsonIgnoreProperties({ "schools", "hibernateLazyInitializer", "handler" })
	private ExamCentre examCentre;

	@OneToMany(mappedBy = "school")
	@JsonIgnoreProperties("school")
	@JsonIgnore
	private List<Student> students;

}
