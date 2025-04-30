package edu.goorm.quiz_service.controller;

import edu.goorm.quiz_service.dto.ProblemResponse;
import edu.goorm.quiz_service.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @GetMapping("/news/{newsId}")
    public ResponseEntity<ProblemResponse> getQuizByNewsId(@PathVariable Long newsId) {
        return ResponseEntity.ok(quizService.getQuizByNewsId(newsId));
    }
} 