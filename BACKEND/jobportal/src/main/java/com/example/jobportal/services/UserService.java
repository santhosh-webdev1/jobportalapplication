package com.example.jobportal.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.jobportal.DTO.JwtResponse;
import com.example.jobportal.DTO.LoginRequest;
import com.example.jobportal.DTO.RegisterRequest;
import com.example.jobportal.entity.User;
import com.example.jobportal.entity.enumeration.Role;
import com.example.jobportal.jwt.JwtUtil;
import com.example.jobportal.repository.UserRepository;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, JwtUtil jwtUtil1){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil1;
    }

    public JwtResponse registerUser(RegisterRequest request){
        
        if(userRepository.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException("Email already exists try with different email id");
        }

        User user = new User();
        
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.valueOf(request.getRole().toUpperCase()));

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        return new JwtResponse(token, user.getEmail(), user.getRole().name());
    }

    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest){

        //Find by Email
        User existingUser = userRepository.findByEmail(loginRequest.getEmail())
                        .orElseThrow(() -> new IllegalArgumentException("Username not found"));

        if(!passwordEncoder.matches(loginRequest.getPassword(), existingUser.getPassword())){
            throw new IllegalArgumentException("Password is incorrect");
        }

        String token = jwtUtil.generateToken(existingUser.getEmail(), existingUser.getRole().name() );
        
        return ResponseEntity.ok(new JwtResponse(token, existingUser.getEmail(), existingUser.getRole().name()));
    }
}
