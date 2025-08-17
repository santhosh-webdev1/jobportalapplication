package com.example.jobportal.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import lombok.Data;

@Data
@Entity
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

    private String targetRole;

    @Enumerated(EnumType.STRING)
    private ExperienceLevel experienceLevel;

    @OneToMany(mappedBy="jobSeeker", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Skill> skills = new ArrayList<>();

    @OneToMany(mappedBy="jobSeeker", cascade= CascadeType.ALL, orphanRemoval=true)
    private List<Course> courses = new ArrayList<>();


    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
}
