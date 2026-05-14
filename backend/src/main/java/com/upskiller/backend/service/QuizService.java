package com.upskiller.backend.service;

import com.upskiller.backend.model.Goal;
import com.upskiller.backend.model.Quiz;
import com.upskiller.backend.repository.GoalRepository;
import com.upskiller.backend.repository.QuizRepository;
import org.springframework.stereotype.Service;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final GoalRepository goalRepository;
    private final AIService aiService;

    public QuizService(QuizRepository quizRepository,
                       GoalRepository goalRepository,
                       AIService aiService) {
        this.quizRepository = quizRepository;
        this.goalRepository = goalRepository;
        this.aiService = aiService;
    }

    public Quiz generateQuiz(Long goalId, String skill) {
        Goal goal = goalRepository.findById(goalId).orElseThrow();

        String prompt = """
                Generate a quiz for skill: %s
                Related to goal: %s

                Return JSON:
                - 5 MCQs with options and correct answer
                - 1 coding question
                """.formatted(skill, goal.getGoalText());

        String aiResponse = aiService.callLLM(prompt);

        Quiz quiz = new Quiz();
        quiz.setGoal(goal);
        quiz.setQuestionsJson(aiResponse);
        return quizRepository.save(quiz);
    }

    public Quiz submitScore(Long quizId, int score) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow();
        quiz.setScore(score);
        return quizRepository.save(quiz);
    }
}
