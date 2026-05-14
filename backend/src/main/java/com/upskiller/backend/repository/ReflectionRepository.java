package com.upskiller.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upskiller.backend.model.Reflection;

public interface ReflectionRepository extends JpaRepository<Reflection, Long> {
}
