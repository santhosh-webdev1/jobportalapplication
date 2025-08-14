package com.example.jobportal.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secure")
public class TestController {

    @PreAuthorize("hasRole('JOBSEEKER')")
    @GetMapping("/hello")
    public String helloSecure() {
        return "You are authorized to view this!";
    }
}
