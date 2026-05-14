package com.upskiller.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upskiller.backend.model.Goal;

public interface GoalRepository extends JpaRepository<Goal, Long> {
    List<Goal> findByUserId(Long userId);
}
