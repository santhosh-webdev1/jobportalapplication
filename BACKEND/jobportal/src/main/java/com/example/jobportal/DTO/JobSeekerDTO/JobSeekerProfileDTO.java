package com.example.jobportal.DTO.JobSeekerDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.example.jobportal.entity.JobSeeker;
import com.example.jobportal.entity.enumeration.ExperienceLevel;
import com.example.jobportal.entity.enumeration.Profiency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobSeekerProfileDTO {

    private String fullName;

    private String email;

    private String phone;

    private String resumeURL;

    private String summary;

    private String location;

    private String targetRole;

    private ExperienceLevel experienceLevel;


    private List<SkillDTO> skills;

    private List<CourseDTO> courses;

    

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SkillDTO{

        private String skillName;
        private Profiency profiency;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CourseDTO{

        private LocalDate startDate;

        private LocalDate endDate;

        private String courseName;

        private String company;

        private String description;

    }

    public JobSeekerProfileDTO(JobSeeker jobSeeker){
        this.fullName = jobSeeker.getFullName();
        this.email = jobSeeker.getEmail();
        this.phone = jobSeeker.getPhone();
        this.location = jobSeeker.getLocation();
        this.resumeURL = jobSeeker.getResumeUrl();
        this.targetRole = jobSeeker.getTargetRole();
        this.summary = jobSeeker.getSummary();
        this.experienceLevel = jobSeeker.getExperienceLevel();

        this.skills = jobSeeker.getSkills().stream()
            .map(skill -> new SkillDTO(
                skill.getSkillName(),
                skill.getProfiency()
            ))
            .collect(Collectors.toList());

        this.courses = jobSeeker.getCourses().stream()
            .map(course -> new CourseDTO(
                course.getStartDate(),
                course.getEndDate(),
                course.getCourseName(),
                course.getCompany(),
                course.getDescription()
            ))
            .collect(Collectors.toList());
    }
    
    
}
