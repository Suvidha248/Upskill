package com.upskiller.backend.service;

import com.upskiller.backend.model.Goal;
import com.upskiller.backend.model.Roadmap;
import com.upskiller.backend.repository.GoalRepository;
import com.upskiller.backend.repository.RoadmapRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoadmapService {

    private final RoadmapRepository roadmapRepository;
    private final GoalRepository goalRepository;
    private final AIService aiService;

    public RoadmapService(RoadmapRepository roadmapRepository,
                          GoalRepository goalRepository,
                          AIService aiService) {
        this.roadmapRepository = roadmapRepository;
        this.goalRepository = goalRepository;
        this.aiService = aiService;
    }

    public List<Roadmap> generateRoadmap(Long goalId, String userHistoryJson) {
        Goal goal = goalRepository.findById(goalId).orElseThrow();

        String prompt = """
                User goal: %s
                Timeline: %s
                User history: %s

                Create a weekly roadmap with:
                - week_number
                - tasks (3-5)
                - resource_links

                Return JSON array.
                """.formatted(goal.getGoalText(), goal.getTimeline(), userHistoryJson);

        String aiResponse = aiService.callLLM(prompt);

        // MVP: store raw JSON as week 1 roadmap
        Roadmap roadmap = new Roadmap();
        roadmap.setGoal(goal);
        roadmap.setWeekNumber(1);
        roadmap.setTasksJson(aiResponse);
        roadmap.setResourcesJson("[]");

        roadmapRepository.save(roadmap);

        return List.of(roadmap);
    }

    public List<Roadmap> getRoadmap(Long goalId) {
        return roadmapRepository.findByGoalId(goalId);
    }
}
