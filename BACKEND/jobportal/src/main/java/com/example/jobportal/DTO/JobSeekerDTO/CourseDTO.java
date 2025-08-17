package com.example.jobportal.DTO.JobSeekerDTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CourseDTO {


    private String courseName;

    private String company;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    

}
