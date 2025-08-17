package com.example.jobportal.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobportal.DTO.JwtResponse;
import com.example.jobportal.DTO.LoginRequest;
import com.example.jobportal.DTO.RegisterRequest;
import com.example.jobportal.jwt.JwtUtil;
import com.example.jobportal.servicesimpl.CustomUserDetailService;
import com.example.jobportal.servicesimpl.UserService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    

    public AuthController(CustomUserDetailService userDetailService, JwtUtil jwtUtil, AuthenticationManager authenticationManager, UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest){
        try {
            JwtResponse response = userService.registerUser(registerRequest);
            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
        
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
            ResponseEntity<JwtResponse> response = userService.login(loginRequest);
            return ResponseEntity.ok(response);
    }


}
