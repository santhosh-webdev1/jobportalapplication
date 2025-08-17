package com.example.jobportal.DTO.JobSeekerDTO;

import com.example.jobportal.entity.enumeration.Profiency;

import lombok.Data;

@Data
public class SkillDTO {

    private String skillName;

    private Profiency profiency;

}
