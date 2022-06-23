package com.example.school_mngt_backend.dtos;

import com.example.school_mngt_backend.models.School;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SchoolDto {
    private String name;
    private String motto;
    @JsonProperty("established_on")
    private String establishedOn;

    public SchoolDto() {
    }

    public SchoolDto(School school) {
        this.name = school.getName();
        this.motto = school.getMotto();
        this.establishedOn = school.getEstablishedOn();
    }

    public SchoolDto(String name, String motto, String establishedOn) {
        this.setName(name);
        this.setMotto(motto);
        this.setEstablishedOn(establishedOn);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getEstablishedOn() {
        return establishedOn;
    }

    public void setEstablishedOn(String establishedOn) {
        this.establishedOn = establishedOn;
    }
}
