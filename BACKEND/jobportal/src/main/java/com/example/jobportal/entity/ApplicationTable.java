package com.example.jobportal.entity;

import java.time.LocalDateTime;

import com.example.jobportal.entity.enumeration.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationTable {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    // field for job application functionality
    @Enumerated(EnumType.STRING)
    private Status status;


    private String resumeLink;


    // Date of applied
    private LocalDateTime appliedDate;
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate(){
        appliedDate = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate(){
        updatedAt = LocalDateTime.now();
    }

    // field of joining columns from the multiple tables
    @ManyToOne
    @JoinColumn(name="job_seeker_id", nullable=false)
    private JobSeeker jobSeeker;

    @ManyToOne
    @JoinColumn(name="recruiter_id", nullable=false)
    private Recruiter recruiter;

    @ManyToOne
    @JoinColumn(name="job_id", nullable=false)
    private JobApplication jobApplication;
    
}
