package com.example.school_mngt_backend.dtos;

import com.example.school_mngt_backend.enums.Degree;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Enumerated;

public class TeacherDto {
    private String names;

    @Enumerated
    private Degree degree;

    @JsonProperty("school_id")
    private Long schoolId;

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }
}
