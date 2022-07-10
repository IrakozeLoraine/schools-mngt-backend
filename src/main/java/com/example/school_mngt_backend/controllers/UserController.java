package com.example.school_mngt_backend.controllers;

import com.example.school_mngt_backend.dtos.LoginDto;
import com.example.school_mngt_backend.dtos.UserDto;
import com.example.school_mngt_backend.models.User;
import com.example.school_mngt_backend.repositories.UserRepository;
import com.example.school_mngt_backend.services.impl.UserServiceImpl;
import com.example.school_mngt_backend.utils.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserServiceImpl userService;
    private final UserRepository userRepository;

    public UserController(UserServiceImpl userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<?> all(){
//        return ResponseEntity.ok(new APIResponse( HttpStatus.OK,"All users",userService.findAll()));
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> save(@RequestBody UserDto dto){
        Optional<User> existingUser = userRepository.findByUsernameAndEmail(dto.getUsername(),dto.getEmail());
        if(existingUser.isPresent()){
            return ResponseEntity.ok(new APIResponse(HttpStatus.OK,"User with email "+dto.getEmail()+" already exists",null));
        }
        return ResponseEntity.ok(new APIResponse( HttpStatus.OK,"User added",userService.save(dto)));
    }
    @PutMapping("/{id}")

    public ResponseEntity<?> update(@RequestBody UserDto dto, @PathVariable Long id){
        return ResponseEntity.ok(new APIResponse( HttpStatus.OK,"User updated",userService.update(dto,id)));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto dto){
        System.out.println("From frontend "+dto.getLogin()+" "+dto.getPassword());
        return ResponseEntity.ok(new APIResponse(HttpStatus.OK, "Welcome Back", userService.login(dto)));
    }
    @GetMapping("/profile")
    public ResponseEntity<?> profile(){
        return ResponseEntity.ok(userService.profile());
    }
}