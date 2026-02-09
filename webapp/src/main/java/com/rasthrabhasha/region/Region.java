package com.rasthrabhasha.region;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rasthrabhasha.examcentre.ExamCentre;
import jakarta.persistence.*;

@Entity
@Table(name = "region")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("regionId")
    private Long regionId;

    @Column(nullable = false, unique = true)
    private String regionName;

    @OneToMany(mappedBy = "region")
    @JsonIgnore
    private List<ExamCentre> examCentres;

    // Default Constructor (CRITICAL for Jackson/Spring)
    public Region() {
    }

    public Region(String regionName) {
        this.regionName = regionName;
    }

    // Getters and Setters
    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public List<ExamCentre> getExamCentres() {
        return examCentres;
    }

    public void setExamCentres(List<ExamCentre> examCentres) {
        this.examCentres = examCentres;
    }

    @Override
    public String toString() {
        System.out.println("The region name is");
        return regionName;
    }
}