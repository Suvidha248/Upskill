package com.upskiller.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upskiller.backend.model.Progress;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
    Optional<Progress> findByGoalId(Long goalId);
}
