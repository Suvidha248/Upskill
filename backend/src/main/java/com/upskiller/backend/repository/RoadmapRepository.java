package com.upskiller.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upskiller.backend.model.Roadmap;

public interface RoadmapRepository extends JpaRepository<Roadmap, Long> {
}
