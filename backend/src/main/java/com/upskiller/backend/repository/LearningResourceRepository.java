package com.upskiller.backend.repository;

import com.upskiller.backend.model.LearningResource;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LearningResourceRepository extends JpaRepository<LearningResource, Long> {

    List<LearningResource> findBySkill(String skill);
}
