package com.example.jobportal.DTO;

import lombok.Data;

@Data
public class JwtResponse {
    private String token;
    private String email;
    private String role;

    public JwtResponse(String token, String email, String role) {
        this.token = token;
        this.email = email;
        this.role = role;
    }

    // getters & setters
}