package com.rasthrabhasha.examcentre;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rasthrabhasha.region.Region;
import com.rasthrabhasha.school.School;

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
@Table(name = "exam_centre")
public class ExamCentre {

	@Override
	public String toString() {
		return "ExamCentre [centreId=" + centreId + ", centreCode=" + centreCode +
				", centreName=" + centreName + ", region=" + (region != null ? region.getRegionName() : "null") + "]";
	}

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long centreId;

	@Column(nullable = false, unique = true)
	private String centreCode;

	private String centreName;

	public Long getCentreId() {
		return centreId;
	}

	public void setCentreId(Long centreId) {
		this.centreId = centreId;
	}

	public String getCentreCode() {
		return centreCode;
	}

	public void setCentreCode(String centreCode) {
		this.centreCode = centreCode;
	}

	public String getCentreName() {
		return centreName;
	}

	public void setCentreName(String centreName) {
		this.centreName = centreName;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public List<School> getSchools() {
		return schools;
	}

	public void setSchools(List<School> schools) {
		this.schools = schools;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "region_id", nullable = false)
	private Region region;

	@OneToMany(mappedBy = "examCentre")
	@JsonIgnore
	private List<School> schools;

	// getters & setters
}
