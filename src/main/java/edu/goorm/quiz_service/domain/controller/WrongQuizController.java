package edu.goorm.quiz_service.domain.controller;

import edu.goorm.quiz_service.domain.dto.WrongQuizDto;
import edu.goorm.quiz_service.global.logger.CustomLogger;
import edu.goorm.quiz_service.domain.service.WrongQuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/quiz/wrong")
@RequiredArgsConstructor
public class WrongQuizController {

    private final WrongQuizService wrongQuizService;

    @PostMapping
    public ResponseEntity<Void> saveWrongQuiz(
        @RequestHeader("X-User-Email") String email,
        @RequestBody WrongQuizDto request,
        HttpServletRequest http) {

        long start = System.currentTimeMillis();
        wrongQuizService.saveWrongQuiz(email, request.getSummaryId());
        long duration = System.currentTimeMillis() - start;

        CustomLogger.logRequest(
            "WRONG_QUIZ_SAVE",
            "/api/quiz/wrong",
            "POST",
            email,
            http,
            200,
            duration
        );

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{summaryId}")
    public ResponseEntity<Void> deleteWrongQuiz(
        @RequestHeader("X-User-Email") String email,
        @PathVariable Long summaryId,
        HttpServletRequest http) {

        long start = System.currentTimeMillis();
        wrongQuizService.deleteWrongQuiz(email, summaryId);
        long duration = System.currentTimeMillis() - start;

        CustomLogger.logRequest(
            "WRONG_QUIZ_DELETE",
            "/api/quiz/wrong/" + summaryId,
            "DELETE",
            email,
            http,
            204,
            duration
        );

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<WrongQuizDto>> getWrongQuizzes(
        @RequestHeader("X-User-Email") String email,
        HttpServletRequest http) {

        long start = System.currentTimeMillis();
        List<WrongQuizDto> wrongQuizzes = wrongQuizService.getWrongQuizzesByEmail(email);
        long duration = System.currentTimeMillis() - start;

        CustomLogger.logRequest(
            "WRONG_QUIZ_GET",
            "/api/quiz/wrong",
            "GET",
            email,
            http,
            200,
            duration
        );

        return ResponseEntity.ok(wrongQuizzes);
    }

    @PatchMapping("/{summaryId}")
    public ResponseEntity<Void> updateWrongQuiz(
        @RequestHeader("X-User-Email") String email,
        @PathVariable Long summaryId,
        @RequestBody WrongQuizDto request,
        HttpServletRequest http) {

        long start = System.currentTimeMillis();
        wrongQuizService.updateWrongQuiz(email, summaryId, request.getStatus());
        long duration = System.currentTimeMillis() - start;

        CustomLogger.logRequest(
            "WRONG_QUIZ_UPDATE",
            "/api/quiz/wrong/" + summaryId,
            "PATCH",
            email,
            http,
            204,
            duration
        );

        return ResponseEntity.noContent().build();
    }
}
