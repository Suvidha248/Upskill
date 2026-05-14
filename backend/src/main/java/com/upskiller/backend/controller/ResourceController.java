package com.upskiller.backend.controller;

import com.upskiller.backend.model.LearningResource;
import com.upskiller.backend.service.ResourceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
@CrossOrigin
public class ResourceController {

    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping("/{skill}")
    public List<LearningResource> getResources(@PathVariable String skill) {
        return resourceService.getResourcesForSkill(skill);
    }
}
