package com.example.jobportal.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.jobportal.DTO.JobSeekerDTO.JobSeekerProfileDTO;
import com.example.jobportal.entity.enumeration.ExperienceLevel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class JobSeeker {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    private String fullName;
    private String email;
    private String phone;
    private String resumeUrl;
    private String location;
    private String summary;

    private String targetRole;

    @Enumerated(EnumType.STRING)
    private ExperienceLevel experienceLevel;

    @OneToMany(mappedBy="jobSeeker", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Skill> skills = new ArrayList<>();

    @OneToMany(mappedBy="jobSeeker", cascade= CascadeType.ALL, orphanRemoval=true)
    private List<Course> courses = new ArrayList<>();


    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();


    public JobSeeker(JobSeekerProfileDTO dto){
    this.fullName = dto.getFullName();
    this.email = dto.getEmail();
    this.phone = dto.getPhone();
    this.location = dto.getLocation();
    this.resumeUrl = dto.getResumeURL();
    this.targetRole = dto.getTargetRole();
    this.summary = dto.getSummary();
    this.experienceLevel = dto.getExperienceLevel();

    // Map skills
    this.skills = dto.getSkills().stream()
    .map(skillDTO -> {
        Skill skill = new Skill();
        skill.setSkillName(skillDTO.getSkillName());
        skill.setProfiency(skillDTO.getProfiency());
        skill.setJobSeeker(this); // important for bidirectional mapping
        return skill;
    })
    .collect(Collectors.toList());

    // Map courses
    this.courses = dto.getCourses().stream()
    .map(courseDTO -> {
        Course course = new Course();
        course.setCourseName(courseDTO.getCourseName());
        course.setCompany(courseDTO.getCompany());
        course.setStartDate(courseDTO.getStartDate());
        course.setEndDate(courseDTO.getEndDate());
        course.setDescription(courseDTO.getDescription());
        course.setJobSeeker(this);
        return course;
    })
    .collect(Collectors.toList());
}

}
