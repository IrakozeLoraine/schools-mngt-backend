package com.example.school_mngt_backend.dtos;

import com.example.school_mngt_backend.enums.EDegree;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Enumerated;

public class TeacherDto {
    private String names;

    @Enumerated
    private EDegree EDegree;

    @JsonProperty("school_id")
    private Long schoolId;

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public EDegree getDegree() {
        return EDegree;
    }

    public void setDegree(EDegree EDegree) {
        this.EDegree = EDegree;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }
}
