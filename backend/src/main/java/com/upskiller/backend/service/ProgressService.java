package com.upskiller.backend.service;

import com.upskiller.backend.model.Goal;
import com.upskiller.backend.model.Progress;
import com.upskiller.backend.repository.GoalRepository;
import com.upskiller.backend.repository.ProgressRepository;
import org.springframework.stereotype.Service;

@Service
public class ProgressService {

    private final ProgressRepository progressRepository;
    private final GoalRepository goalRepository;

    public ProgressService(ProgressRepository progressRepository,
                           GoalRepository goalRepository) {
        this.progressRepository = progressRepository;
        this.goalRepository = goalRepository;
    }

    public Progress updateProgress(Long goalId, int completedTasks, int totalTasks, String weakAreasJson) {
        Goal goal = goalRepository.findById(goalId).orElseThrow();
        Progress progress = progressRepository.findByGoalId(goalId).orElse(new Progress());
        progress.setGoal(goal);
        progress.setCompletedTasks(completedTasks);
        progress.setTotalTasks(totalTasks);
        progress.setWeakAreasJson(weakAreasJson);
        return progressRepository.save(progress);
    }

    public Progress getProgress(Long goalId) {
        return progressRepository.findByGoalId(goalId).orElse(null);
    }
}
