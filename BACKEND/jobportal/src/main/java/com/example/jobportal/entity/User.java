package com.example.jobportal.entity;

import com.example.jobportal.entity.enumeration.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Role role;

    @Column(nullable=false, updatable=false)
    private java.time.LocalDateTime createdAt;

    @PrePersist
    public void prePersist(){
        createdAt = java.time.LocalDateTime.now();
    }

    @OneToOne(mappedBy="user", cascade=CascadeType.ALL)
    private JobSeeker jobSeeker;

    @OneToOne(mappedBy="user", cascade=CascadeType.ALL)
    private Recruiter recruiter;

}
