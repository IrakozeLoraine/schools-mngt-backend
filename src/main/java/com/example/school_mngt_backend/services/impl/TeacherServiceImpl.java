package com.example.school_mngt_backend.services.impl;

import com.example.school_mngt_backend.dtos.TeacherDto;
import com.example.school_mngt_backend.models.School;
import com.example.school_mngt_backend.models.Teacher;
import com.example.school_mngt_backend.repositories.TeacherRepository;
import com.example.school_mngt_backend.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SchoolServiceImpl schoolService;

    @Override
    public Teacher save(TeacherDto teacherDto) throws Exception {
        Optional<School> school = schoolService.getById(teacherDto.getSchoolId());

        List<School> schools = new ArrayList<>();
        schools.add(school.get());

        return teacherRepository.save(
                new Teacher(
                        teacherDto.getNames(),
                        teacherDto.getDegree(),
                       schools
                )
        );
    }

    @Override
    public List<Teacher> getAll() throws Exception {
        return teacherRepository.findAll();
    }

    @Override
    public Optional<Teacher> getById(Long id) throws Exception {
        return teacherRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        teacherRepository.deleteById(id);
    }

    @Override
    public List<Teacher> getAllTeachersBySchoolId(Long id) throws Exception {
        Optional<School> school = schoolService.getById(id);
        return teacherRepository.findBySchools(school.get());
    }
}
