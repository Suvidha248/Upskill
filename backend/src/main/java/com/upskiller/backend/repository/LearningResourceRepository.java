package com.upskiller.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upskiller.backend.model.LearningResource;

public interface LearningResourceRepository extends JpaRepository<LearningResource, Long> {
}
