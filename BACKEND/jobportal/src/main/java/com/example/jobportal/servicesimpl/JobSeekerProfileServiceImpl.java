package com.example.jobportal.servicesimpl;



import org.springframework.stereotype.Service;

import com.example.jobportal.DTO.JobSeekerDTO.JobSeekerProfileDTO;
import com.example.jobportal.entity.JobSeeker;
import com.example.jobportal.entity.User;
import com.example.jobportal.repository.JobSeekerRepository;
import com.example.jobportal.repository.UserRepository;
import com.example.jobportal.service.JobSeekerProfileService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class JobSeekerProfileServiceImpl  implements JobSeekerProfileService{


    private final JobSeekerRepository jobSeekerRepository;

    private final UserRepository userRepository;


    @Override
    public JobSeekerProfileDTO createProfile(Long userId, JobSeekerProfileDTO jobSeekerProfileDTO) {
        
        // getting the user entity to set the jobSeeker userId
        User user = userRepository.findById(userId)
                                .orElseThrow(() -> new IllegalArgumentException("UserData not found"));

        // getting the jobseeker from the userId
        // JobSeeker jobSeeker = jobSeekerRepository.findByUserId(userId)
        //                             .orElseThrow(() -> new IllegalArgumentException("Jobseeker not found"));

        JobSeeker profile = new JobSeeker(jobSeekerProfileDTO);
        profile.setUser(user);

        // populate the fields of jobSeeker
        // JobSeeker profile = new JobSeeker(jobSeekerProfileDTO);
        // profile.setUser(user);

        // store it on the database
        jobSeekerRepository.save(profile);

        //Generate and return the response
        return new JobSeekerProfileDTO(profile);

    }


    @Override
    public JobSeekerProfileDTO getProfile(Long userId) {

        JobSeeker profile = jobSeekerRepository.findByUserId(userId)
                                        .orElseThrow(() -> new IllegalArgumentException("Profile Not Found"));


        JobSeekerProfileDTO response = new JobSeekerProfileDTO(profile);

        return response;
        
    }

    @Override
    public JobSeekerProfileDTO updateProfile(Long jobseekerId, JobSeekerProfileDTO jobSeekerProfileDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProfile'");
    }

    @Override
    public void deleteProfile(Long jobseekerId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteProfile'");
    }

    







}
