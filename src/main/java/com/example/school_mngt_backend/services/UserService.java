package com.example.school_mngt_backend.services;

import com.example.school_mngt_backend.dtos.LoginDto;
import com.example.school_mngt_backend.dtos.UserDto;
import com.example.school_mngt_backend.models.User;

import java.util.List;

public interface UserService {
    User save(UserDto user);
    User findById(Long id);
    User update(UserDto user, Long id);
    User remove(Long id);
    List<User> findAll();
    String login(LoginDto dto);

    User findByUserNameOrEmail(String username, String email);

    User profile();
}
