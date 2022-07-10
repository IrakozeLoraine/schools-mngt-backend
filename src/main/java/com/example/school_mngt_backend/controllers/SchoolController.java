package com.example.school_mngt_backend.controllers;

import com.example.school_mngt_backend.utils.APIResponse;
import com.example.school_mngt_backend.dtos.SchoolDto;
import com.example.school_mngt_backend.models.School;
import com.example.school_mngt_backend.services.SchoolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/schools")
public class SchoolController {
    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping()
    public ResponseEntity<APIResponse> getAllSchools() throws Exception {
        List<School> schools = schoolService.getAll();
        return ResponseEntity.ok().body(new APIResponse(HttpStatus.OK, "", schools));
    }
    @PostMapping()
    public ResponseEntity<APIResponse> createSchool(@Valid @RequestBody SchoolDto schoolDto) throws Exception {
        School school = schoolService.save(schoolDto);
        return ResponseEntity.ok().body(new APIResponse(HttpStatus.OK, "SUCCESSFULLY RECORDED", school));
    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getSchoolById(@PathVariable Long id) throws Exception {
        Optional<School> school = schoolService.getById(id);
        return ResponseEntity.ok().body(new APIResponse(HttpStatus.OK, "SUCCESSFULLY RECORDED", school));
    }
    @PutMapping()
    public ResponseEntity<APIResponse> updateSchool(@Valid @RequestBody SchoolDto schoolDto) throws Exception {
        School school = schoolService.save(schoolDto);
        return ResponseEntity.ok().body(new APIResponse(HttpStatus.OK, "SUCCESSFULLY RECORDED", school));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteSchoolById(@PathVariable Long id) throws Exception {
        schoolService.deleteById(id);
        return ResponseEntity.ok().body(new APIResponse(HttpStatus.OK, "SUCCESSFULLY RECORDED", null));
    }
}
