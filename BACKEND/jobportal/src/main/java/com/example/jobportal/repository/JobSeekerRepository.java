package com.example.jobportal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jobportal.entity.JobSeeker;

public interface JobSeekerRepository extends JpaRepository<JobSeeker, Long>{

    Optional<JobSeeker> findByUserId(Long userId);

    boolean existsByUserId(Long userId);

}
