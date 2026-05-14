package com.upskiller.backend.controller;

import com.upskiller.backend.model.Goal;
import com.upskiller.backend.service.GoalService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/goal")
@CrossOrigin
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    public static record CreateGoalRequest(Long userId, String goalText, String timeline) {}

    @PostMapping
    public Goal createGoal(@RequestBody CreateGoalRequest req) {
        return goalService.createGoal(req.userId(), req.goalText(), req.timeline());
    }

    @GetMapping("/{goalId}")
    public Goal getGoal(@PathVariable Long goalId) {
        return goalService.getGoal(goalId);
    }
}
