package com.upskiller.backend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private final ObjectMapper mapper = new ObjectMapper();

    public RoadmapService(RoadmapRepository roadmapRepository,
                          GoalRepository goalRepository,
                          AIService aiService) {
        this.roadmapRepository = roadmapRepository;
        this.goalRepository = goalRepository;
        this.aiService = aiService;
    }

    // 🔥 Helper: Extract ONLY the JSON array from AI output
    private String extractJsonArray(String text) {
        int start = text.indexOf("[");
        int end = text.lastIndexOf("]");

        if (start == -1 || end == -1 || end <= start) {
            throw new RuntimeException("AI did not return a JSON array: " + text);
        }

        return text.substring(start, end + 1);
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

                Return ONLY a valid JSON array.
                No markdown. No explanation. No comments.
                """.formatted(goal.getGoalText(), goal.getTimeline(), userHistoryJson);

        // 🔥 Call LLM
        String aiResponse = aiService.callLLM(prompt);

        // 🔥 Extract JSON array only
        String cleaned = extractJsonArray(aiResponse);

        // 🔥 Validate JSON
        JsonNode json;
        try {
            json = mapper.readTree(cleaned);
        } catch (Exception e) {
            throw new RuntimeException("AI returned invalid JSON: " + cleaned);
        }

        // 🔥 Save clean JSON
        Roadmap roadmap = new Roadmap();
        roadmap.setGoal(goal);
        roadmap.setWeekNumber(1);
        roadmap.setTasksJson(json.toString());
        roadmap.setResourcesJson("[]");

        roadmapRepository.save(roadmap);

        return List.of(roadmap);
    }

    public List<Roadmap> getRoadmap(Long goalId) {
        return roadmapRepository.findByGoalId(goalId);
    }
}
