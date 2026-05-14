package com.upskiller.backend.service;

import com.upskiller.backend.model.Goal;
import com.upskiller.backend.model.Reflection;
import com.upskiller.backend.repository.GoalRepository;
import com.upskiller.backend.repository.ReflectionRepository;
import org.springframework.stereotype.Service;

@Service
public class ReflectionService {

    private final ReflectionRepository reflectionRepository;
    private final GoalRepository goalRepository;
    private final AIService aiService;

    public ReflectionService(ReflectionRepository reflectionRepository,
                             GoalRepository goalRepository,
                             AIService aiService) {
        this.reflectionRepository = reflectionRepository;
        this.goalRepository = goalRepository;
        this.aiService = aiService;
    }

    public Reflection createReflection(Long goalId, String reflectionText, String weakAreasJson) {
        Goal goal = goalRepository.findById(goalId).orElseThrow();

        String prompt = """
                User reflection: %s
                Past weak areas: %s

                Return JSON:
                - understood
                - missed
                - updated_weak_areas
                - recommended_next_tasks
                """.formatted(reflectionText, weakAreasJson);

        String aiResponse = aiService.callLLM(prompt);

        Reflection reflection = new Reflection();
        reflection.setGoal(goal);
        reflection.setReflectionText(reflectionText);
        reflection.setAiFeedback(aiResponse);

        return reflectionRepository.save(reflection);
    }
}
