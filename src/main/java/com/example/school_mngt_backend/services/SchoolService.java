package com.example.school_mngt_backend.services;

import com.example.school_mngt_backend.dtos.SchoolDto;
import com.example.school_mngt_backend.models.School;

import java.util.List;
import java.util.Optional;

public interface SchoolService {
    public School save(SchoolDto schoolDto) throws Exception;
    public List<School> getAll() throws Exception;
    public Optional<School> getById(Long id) throws Exception;
    public void deleteById(Long id) throws Exception;
}
