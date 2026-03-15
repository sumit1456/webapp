package com.rasthrabhasha.common;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    @Column(name = "address_line1")
    private String line1;

    @Column(name = "address_line2")
    private String line2;

    @Column(name = "village_or_city")
    private String villageOrCity;

    @Column(name = "taluka")
    private String taluka;

    @Column(name = "district")
    private String district;

    @Column(name = "state")
    private String state;

    @Column(name = "pincode")
    private String pincode;

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getVillageOrCity() {
        return villageOrCity;
    }

    public void setVillageOrCity(String villageOrCity) {
        this.villageOrCity = villageOrCity;
    }

    public String getTaluka() {
        return taluka;
    }

    public void setTaluka(String taluka) {
        this.taluka = taluka;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
