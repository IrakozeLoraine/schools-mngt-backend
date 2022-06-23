package com.example.school_mngt_backend.repositories;

import com.example.school_mngt_backend.models.School;
import com.example.school_mngt_backend.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    public List<Teacher> findBySchools(School school);
}
