package com.upskiller.backend.controller;

import com.upskiller.backend.model.Quiz;
import com.upskiller.backend.service.QuizService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quiz")
@CrossOrigin
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    public static record GenerateQuizRequest(Long goalId, String skill) {}
    public static record SubmitQuizRequest(Integer score) {}

    @PostMapping("/generate")
    public Quiz generate(@RequestBody GenerateQuizRequest req) {
        return quizService.generateQuiz(req.goalId(), req.skill());
    }

    @PostMapping("/{quizId}/submit")
    public Quiz submit(@PathVariable Long quizId, @RequestBody SubmitQuizRequest req) {
        return quizService.submitScore(quizId, req.score());
    }
}
