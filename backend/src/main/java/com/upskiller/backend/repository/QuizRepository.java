package com.upskiller.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upskiller.backend.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
