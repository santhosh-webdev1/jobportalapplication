package com.example.jobportal.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.User;
import com.example.jobportal.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{

    private final UserRepository userRepository;

    // constructor
    public CustomUserDetailService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Username not found" + email));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    
}
