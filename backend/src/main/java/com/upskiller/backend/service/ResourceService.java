package com.upskiller.backend.service;

import com.upskiller.backend.model.LearningResource;
import com.upskiller.backend.repository.LearningResourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    private final LearningResourceRepository learningResourceRepository;

    public ResourceService(LearningResourceRepository learningResourceRepository) {
        this.learningResourceRepository = learningResourceRepository;
    }

    public List<LearningResource> getResourcesForSkill(String skill) {
        return learningResourceRepository.findBySkill(skill);
    }
}
