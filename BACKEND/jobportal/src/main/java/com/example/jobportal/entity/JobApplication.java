package com.example.jobportal.entity;

import java.time.LocalDate;

import com.example.jobportal.entity.enumeration.ExperienceLevel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class JobApplication {

    // primary key for job application
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    // ref id
    private String refId;

    // recruiter id ManyToOne relationship
    @ManyToOne
    @JoinColumn(name="recruiter_id")
    private Recruiter recruiter;

    // job name
    private String jobTitle;

    @Column(length=2000)
    private String jobDescription;

    // salary range
    private Double minSalary;
    private Double maxSalary;

    // Expectations and Requirements
    private String qualification;
    private String requiredSkills;

    // experience and location
    private String workLocation;

    @Enumerated(EnumType.STRING)
    private ExperienceLevel experienceLevel;

    // application starts and endDate
    private LocalDate postedDate;
    private LocalDate deadlineDate;
    

}
