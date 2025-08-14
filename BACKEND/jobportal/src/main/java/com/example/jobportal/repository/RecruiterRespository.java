package com.example.jobportal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jobportal.entity.Recruiter;

public interface RecruiterRespository extends JpaRepository<Recruiter, Long>{

    Optional<Recruiter> findByUserId(Long userId);

    boolean existsByUserId(Long userId);
}
