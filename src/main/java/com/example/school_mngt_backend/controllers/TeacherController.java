package com.example.school_mngt_backend.controllers;

import com.example.school_mngt_backend.utils.APIResponse;
import com.example.school_mngt_backend.dtos.TeacherDto;
import com.example.school_mngt_backend.models.Teacher;
import com.example.school_mngt_backend.services.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/teachers")
public class TeacherController {

    @Autowired
    private TeacherServiceImpl teacherService;

    @GetMapping()
    public ResponseEntity<APIResponse> getAllTeachers() throws Exception {
        List<Teacher> teachers = teacherService.getAll();
        return ResponseEntity.ok().body(new APIResponse(HttpStatus.OK, "", teachers));
    }
    @PostMapping()
    public ResponseEntity<APIResponse> createTeacher(@Valid @RequestBody TeacherDto teacherDto) throws Exception {
        Teacher teacher = teacherService.save(teacherDto);
        return ResponseEntity.ok().body(new APIResponse(HttpStatus.OK, "SUCCESSFULLY RECORDED", teacher));
    }
    @GetMapping("/by-school/{id}")
    public ResponseEntity<APIResponse> getTeacherBySchoolId(@PathVariable Long id) throws Exception {
        List<Teacher> teachers = teacherService.getAllTeachersBySchoolId(id);
        return ResponseEntity.ok().body(new APIResponse(HttpStatus.OK, "SUCCESSFULLY RECORDED", teachers));
    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getSchoolById(@PathVariable Long id) throws Exception {
        Optional<Teacher> teacher = teacherService.getById(id);
        return ResponseEntity.ok().body(new APIResponse(HttpStatus.OK, "SUCCESSFULLY RECORDED", teacher));
    }
    @PutMapping()
    public ResponseEntity<APIResponse> updateSchool(@Valid @RequestBody TeacherDto teacherDto) throws Exception {
        Teacher teacher = teacherService.save(teacherDto);
        return ResponseEntity.ok().body(new APIResponse(HttpStatus.OK, "SUCCESSFULLY RECORDED", teacher));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteSchoolById(@PathVariable Long id) throws Exception {
        teacherService.deleteById(id);
        return ResponseEntity.ok().body(new APIResponse(HttpStatus.OK, "SUCCESSFULLY RECORDED", null));
    }
}
