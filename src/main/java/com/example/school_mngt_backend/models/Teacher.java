package com.example.school_mngt_backend.models;

import com.example.school_mngt_backend.enums.EDegree;

import javax.persistence.*;
import java.util.List;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String names;

    @Enumerated
    EDegree EDegree;

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

    public Teacher(String names, EDegree EDegree, List<School> schools) {
        this.names = names;
        this.EDegree = EDegree;
        this.schools = schools;
    }

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

    public List<School> getSchools() {
        return schools;
    }

    public void setSchools(List<School> schools) {
        this.schools = schools;
    }
}
