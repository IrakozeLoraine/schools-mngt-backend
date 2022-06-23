package com.example.school_mngt_backend.services.impl;

import com.example.school_mngt_backend.dtos.SchoolDto;
import com.example.school_mngt_backend.exceptions.AlreadyExistsException;
import com.example.school_mngt_backend.models.School;
import com.example.school_mngt_backend.repositories.SchoolRepository;
import com.example.school_mngt_backend.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.naming.directory.InvalidAttributesException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    private final SchoolRepository schoolRepository;

    public SchoolServiceImpl(SchoolRepository schoolRepository){
        this.schoolRepository = schoolRepository;
    }

    @Transactional
    @Override
    public School save(SchoolDto dto) throws Exception {

        if(schoolRepository.existsByName(dto.getName())) {
            throw new AlreadyExistsException("School Name");
        }

        if (dto.getName().equals("")){
            throw new InvalidAttributesException("School Name is required");
        }
        if (dto.getEstablishedOn().equals("")){
            throw new InvalidAttributesException("School establishment date is required");
        }
        return schoolRepository.save(new School(dto));

    }

    @Override
    public List<School> getAll() {
        return schoolRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public Optional<School> getById(Long id) throws Exception {
        Optional<School> school = schoolRepository.findById(id);
        if (!school.isPresent()){
            throw new EntityNotFoundException("School not found");
        }
        return school;
    }

    @Override
    public void deleteById(Long id) {
        Optional<School> school = schoolRepository.findById(id);
        if (!school.isPresent()){
            throw new EntityNotFoundException("School not found");
        }
        schoolRepository.deleteById(id);
    }
}
