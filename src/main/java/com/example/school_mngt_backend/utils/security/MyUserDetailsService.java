package com.example.school_mngt_backend.utils.security;

import com.example.school_mngt_backend.exceptions.ResourceNotFoundException;
import com.example.school_mngt_backend.models.User;
import com.example.school_mngt_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmailOrPhone(username, username,username).orElseThrow(() -> new ResourceNotFoundException("user not found with username or email or mobile of " + username));
        return UserPrinciple.create(user);
    }

    @Transactional
    public UserDetails loadByUserId(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
        return UserPrinciple.create(user);
    }
}