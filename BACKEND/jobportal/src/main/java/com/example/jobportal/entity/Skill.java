package com.example.jobportal.entity;

import com.example.jobportal.entity.enumeration.Profiency;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Table(name="skills")
@Entity
@Data
@NoArgsConstructor
public class Skill {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    private Profiency profiency;

    private String skillName;

    @ManyToOne
    @JoinColumn(name="job_seeker_id", nullable=false)
    private JobSeeker jobSeeker;

}
