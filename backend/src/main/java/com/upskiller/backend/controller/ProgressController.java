package com.upskiller.backend.controller;

import com.upskiller.backend.model.Progress;
import com.upskiller.backend.service.ProgressService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/progress")
@CrossOrigin
public class ProgressController {

    private final ProgressService progressService;

    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    public static record UpdateProgressRequest(
            Long goalId,
            Integer completedTasks,
            Integer totalTasks,
            String weakAreasJson
    ) {}

    @PostMapping
    public Progress update(@RequestBody UpdateProgressRequest req) {
        return progressService.updateProgress(
                req.goalId(),
                req.completedTasks(),
                req.totalTasks(),
                req.weakAreasJson()
        );
    }

    @GetMapping("/{goalId}")
    public Progress get(@PathVariable Long goalId) {
        return progressService.getProgress(goalId);
    }
}
