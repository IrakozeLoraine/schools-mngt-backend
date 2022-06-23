package com.example.school_mngt_backend.services;
import com.example.school_mngt_backend.dtos.TeacherDto;
import com.example.school_mngt_backend.models.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    public Teacher save(TeacherDto teacherDto) throws Exception;
    public List<Teacher> getAll() throws Exception;
    public Optional<Teacher> getById(Long id) throws Exception;
    public void deleteById(Long id) throws Exception;
    public List<Teacher> getAllTeachersBySchoolId(Long id) throws Exception;
}
