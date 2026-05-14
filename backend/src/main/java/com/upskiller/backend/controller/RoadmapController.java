package com.upskiller.backend.controller;

import com.upskiller.backend.model.Roadmap;
import com.upskiller.backend.service.RoadmapService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roadmap")
@CrossOrigin
public class RoadmapController {

    private final RoadmapService roadmapService;

    public RoadmapController(RoadmapService roadmapService) {
        this.roadmapService = roadmapService;
    }

    public static record GenerateRoadmapRequest(Long goalId, String userHistoryJson) {}

    @PostMapping("/generate")
    public List<Roadmap> generate(@RequestBody GenerateRoadmapRequest req) {
        return roadmapService.generateRoadmap(req.goalId(), req.userHistoryJson());
    }

    @GetMapping("/{goalId}")
    public List<Roadmap> get(@PathVariable Long goalId) {
        return roadmapService.getRoadmap(goalId);
    }
}
