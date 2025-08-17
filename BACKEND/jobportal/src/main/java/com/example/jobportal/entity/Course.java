package com.example.jobportal.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    private LocalDate startDate;

    private LocalDate endDate;

    private String courseName;

    private String Company;

    private String description;

    @ManyToOne
    @JoinColumn(name="jobseeker_id")
    private JobSeeker jobSeeker;

}
