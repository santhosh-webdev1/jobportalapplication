package com.example.jobportal.servicesimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.jobportal.DTO.JobSeekerDTO.CourseDTO;
import com.example.jobportal.DTO.JobSeekerDTO.JobSeekerProfileDTO;
import com.example.jobportal.DTO.JobSeekerDTO.SkillDTO;
import com.example.jobportal.entity.Course;
import com.example.jobportal.entity.JobSeeker;
import com.example.jobportal.entity.Skill;
import com.example.jobportal.entity.User;
import com.example.jobportal.entity.enumeration.Role;
import com.example.jobportal.repository.JobSeekerRepository;
import com.example.jobportal.repository.UserRepository;
import com.example.jobportal.service.JobSeekerProfileService;

import jakarta.transaction.Transactional;

@Service
public class JobSeekerProfileServiceImpl  implements JobSeekerProfileService{

    
    private final UserRepository userRepository;

    private final JobSeekerRepository jobSeekerRepository;

    public JobSeekerProfileServiceImpl(UserRepository userRepository, JobSeekerRepository jobSeekerRepository){
        this.userRepository = userRepository;
        this.jobSeekerRepository = jobSeekerRepository;
    }



    @Override
    @Transactional
    public JobSeekerProfileDTO createProfile(Long userId, JobSeekerProfileDTO dto) {
        
        User user = userRepository.findById(userId)
                                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if(user.getRole() != Role.JOBSEEKER){
            throw new IllegalArgumentException("User is not a Job Seeker");
        }

        JobSeeker profile = new JobSeeker();
        profile.setUser(user);
        profile.setFullName(dto.getFullName());
        profile.setEmail(dto.getEmail());
        profile.setPhone(dto.getPhone());
        profile.setLocation(dto.getLocation());
        profile.setExperienceLevel(dto.getExperienceLevel());
        profile.setSummary(dto.getSummary());
        
        profile.setResumeUrl(dto.getResumeURL());
        profile.setTargetRole(dto.getTargetRole());

        // mapping the skills
        for(SkillDTO skillDTO : dto.getSkills()){
            Skill skill = new Skill();
            skill.setSkillName(skillDTO.getSkillName());
            skill.setProfiency(skillDTO.getProfiency());
            skill.setJobSeeker(profile);

            // to store it on the jobseeker skill list
            profile.getSkills().add(skill);
        }

        for(CourseDTO courseDTO : dto.getCourses()){
            Course course = new Course();
            course.setCompany(courseDTO.getCompany());
            course.setCourseName(courseDTO.getCourseName());
            course.setDescription(courseDTO.getDescription());
            course.setStartDate(courseDTO.getStartDate());
            course.setEndDate(courseDTO.getEndDate());
            course.setJobSeeker(profile);

            // to store it on the jobseeker courese list
            profile.getCourses().add(course);
            
        }

        jobSeekerRepository.save(profile);
        
        // Prepare response DTO
        JobSeekerProfileDTO response = new JobSeekerProfileDTO();
        response.setFullName(profile.getFullName());
        response.setEmail(profile.getEmail());
        response.setPhone(profile.getPhone());
        response.setLocation(profile.getLocation());
        response.setExperienceLevel(profile.getExperienceLevel());
        response.setSummary(profile.getSummary());
        response.setResumeURL(profile.getResumeUrl());
        response.setTargetRole(profile.getTargetRole());

        // Map skills to DTO
        List<SkillDTO> skillDTOs = new ArrayList<>();
        for(Skill skill : profile.getSkills()){
            SkillDTO sDto = new SkillDTO();
            sDto.setSkillName(skill.getSkillName());
            sDto.setProfiency(skill.getProfiency());
            skillDTOs.add(sDto);
        }
        response.setSkills(skillDTOs);

        // Map courses to DTO
        List<CourseDTO> courseDTOs = new ArrayList<>();
        for(Course course : profile.getCourses()){
            CourseDTO cDto = new CourseDTO();
            cDto.setCompany(course.getCompany());
            cDto.setCourseName(course.getCourseName());
            cDto.setDescription(course.getDescription());
            cDto.setStartDate(course.getStartDate());
            cDto.setEndDate(course.getEndDate());
            courseDTOs.add(cDto);
        }
        response.setCourses(courseDTOs);

        return response;
    }

    @Override
    public JobSeekerProfileDTO getProfile(int jobseekerId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProfile'");
    }

    @Override
    public JobSeekerProfileDTO updateProfile(int jobseekerId, JobSeekerProfileDTO jobSeekerProfileDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProfile'");
    }

    @Override
    public void deleteProfile(int jobseekerId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteProfile'");
    }


}
