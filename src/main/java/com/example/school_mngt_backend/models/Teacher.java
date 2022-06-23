package com.example.school_mngt_backend.models;

import com.example.school_mngt_backend.dtos.TeacherDto;
import com.example.school_mngt_backend.enums.Degree;

import javax.persistence.*;
import java.util.List;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String names;

    @Enumerated
    Degree degree;

    @ManyToMany
    List <School> schools;

    public Teacher() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Teacher(String names, Degree degree, List<School> schools) {
        this.names = names;
        this.degree = degree;
        this.schools = schools;
    }

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

    public List<School> getSchools() {
        return schools;
    }

    public void setSchools(List<School> schools) {
        this.schools = schools;
    }
}
