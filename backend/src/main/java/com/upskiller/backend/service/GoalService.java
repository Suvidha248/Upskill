package com.upskiller.backend.service;

import org.springframework.stereotype.Service;

import com.upskiller.backend.model.Goal;
import com.upskiller.backend.model.User;
import com.upskiller.backend.repository.GoalRepository;
import com.upskiller.backend.repository.UserRepository;

@Service
public class GoalService {

    private final GoalRepository goalRepository;
    private final UserRepository userRepository;

    public GoalService(GoalRepository goalRepository, UserRepository userRepository) {
        this.goalRepository = goalRepository;
        this.userRepository = userRepository;
    }

    public Goal createGoal(Long userId, String goalText, String timeline) {
        User user = userRepository.findById(userId).orElseThrow();
        Goal goal = new Goal();
        goal.setUser(user);
        goal.setGoalText(goalText);
        goal.setTimeline(timeline);
        return goalRepository.save(goal);
    }

    public Goal getGoal(Long goalId) {
        return goalRepository.findById(goalId).orElseThrow();
    }
}
