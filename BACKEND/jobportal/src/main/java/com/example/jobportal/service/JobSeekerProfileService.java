package com.example.jobportal.service;


import com.example.jobportal.DTO.JobSeekerDTO.JobSeekerProfileDTO;


public interface JobSeekerProfileService {

    // jobseeker can create, read, update, delete their data

    JobSeekerProfileDTO createProfile(Long userId, JobSeekerProfileDTO jobSeekerProfileDTO);

    JobSeekerProfileDTO getProfile(Long jobseekerId);

    JobSeekerProfileDTO updateProfile(Long jobseekerId, JobSeekerProfileDTO jobSeekerProfileDTO);

    void deleteProfile(Long jobseekerId);
}
