package com.example.school_mngt_backend.models;

import com.example.school_mngt_backend.dtos.SchoolDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String motto;
    private String establishedOn;

    public School() {
    }

    public School(Long id, String name, String motto, String establishedOn) {
        this.id = id;
        this.name = name;
        this.motto = motto;
        this.establishedOn = establishedOn;
    }

    public School(SchoolDto dto) {
        this.name = dto.getName();
        this.motto = dto.getMotto();
        this.establishedOn = dto.getEstablishedOn();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", motto='" + motto + '\'' +
                ", establishedOn='" + establishedOn + '\'' +
                '}';
    }
}
