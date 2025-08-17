package com.example.jobportal.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Recruiter {

    //Recruiter Id
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    // common user id
    @OneToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    // reference id generation for the recruiter
    private String refId;


    private String fullName;
    private String email;
    private String Phone;

    private String companyName;
    private String companyDescription;
    private String companyLocation;
    
    private List<JobApplication> jobApplication = new ArrayList<>();






}
