package com.example.school_mngt_backend.services.impl;

import com.example.school_mngt_backend.dtos.LoginDto;
import com.example.school_mngt_backend.dtos.UserDto;
import com.example.school_mngt_backend.exceptions.ResourceNotFoundException;
import com.example.school_mngt_backend.models.User;
import com.example.school_mngt_backend.repositories.UserRepository;
import com.example.school_mngt_backend.services.UserService;
import com.example.school_mngt_backend.utils.PasswordEncoder;
import com.example.school_mngt_backend.utils.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public UserServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public User findByUserNameOrEmail(String username, String email) {
        return userRepository.findByUsernameAndEmail(username, email).orElseThrow(()-> new ResourceNotFoundException("User with username " + username + " or email " + email + " already exists"));
    }

    @Override
    public User profile() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser")
            throw new ResourceNotFoundException("You are not logged in, try to log in");

        String email;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }
        return userRepository.findByUsernameOrEmailOrPhone(email,email, email).orElseThrow(() -> new ResourceNotFoundException("User", "email", email));

    }

    @Override
    public User save(UserDto user) {
        User saved = new User(user);
        saved.setPassword(PasswordEncoder.encodePassword(user.getPassword()));
        return userRepository.save(saved);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User with id " + id.toString() + " not found"));
    }


    @Override
    public User update(UserDto user, Long id) {
        User existingUser = findById(id);
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhone(user.getPhone());
        existingUser.setPassword(PasswordEncoder.encodePassword(user.getPassword()));
        return userRepository.save(existingUser);
    }

    @Override
    public User remove(Long id) {
        User existingUser = findById(id);
        userRepository.delete(existingUser);
        return existingUser;
    }
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public String login(LoginDto dto) {
        String jwt = null;
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getPassword()));
        try{
            SecurityContextHolder.getContext().setAuthentication(authentication);
            jwt = jwtTokenProvider.generateToken(authentication);
        }catch (Exception e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return jwt;
    }
}
