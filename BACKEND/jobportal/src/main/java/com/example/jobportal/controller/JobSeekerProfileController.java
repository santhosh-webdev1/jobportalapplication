package com.example.jobportal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobportal.DTO.JobSeekerDTO.JobSeekerProfileDTO;
import com.example.jobportal.entity.User;
import com.example.jobportal.service.JobSeekerProfileService;
import com.example.jobportal.servicesimpl.CustomUserDetailService;

@RestController
@RequestMapping("/jobseeker/profile")
public class JobSeekerProfileController {


    private final JobSeekerProfileService jobSeekerProfileService;
    private final CustomUserDetailService customUserDetailService;

    public JobSeekerProfileController(JobSeekerProfileService jobSeekerProfileService, CustomUserDetailService customUserDetailService){
        this.jobSeekerProfileService = jobSeekerProfileService;
        this.customUserDetailService = customUserDetailService;
    }

    @PostMapping("/create")
    public ResponseEntity<JobSeekerProfileDTO> createProfile(@AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails, @RequestBody JobSeekerProfileDTO dto){
        
        String email = userDetails.getUsername();

        User user = customUserDetailService.findByEmail(email)
                                    .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        JobSeekerProfileDTO response = jobSeekerProfileService.createProfile(user.getId(), dto);

        return ResponseEntity.ok(response);
    }

    


}
