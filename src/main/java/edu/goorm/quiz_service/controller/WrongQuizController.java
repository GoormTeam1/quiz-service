package edu.goorm.quiz_service.controller;

import edu.goorm.quiz_service.dto.WrongQuizDto;
import edu.goorm.quiz_service.logger.CustomLogger;
import edu.goorm.quiz_service.service.WrongQuizService;

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
        wrongQuizService.saveWrongQuiz(email, request.getSummaryId());

        // ✅ 로그 기록
        CustomLogger.logRequest2(
                "WRONG_QUIZ_SAVE",
                "/api/quiz/wrong",
                "POST",
                email,
                null,
                http
        );
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{summaryId}")
    public ResponseEntity<Void> deleteWrongQuiz(
            @RequestHeader("X-User-Email") String email,
            @PathVariable Long summaryId,
            HttpServletRequest http) {
        CustomLogger.logRequest2(
                "WRONG_QUIZ_DELETE",
                "/api/quiz/wrong/" + summaryId,
                "DELETE",
                email,
                null,
                http
        );
        wrongQuizService.deleteWrongQuiz(email, summaryId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<WrongQuizDto>> getWrongQuizzes(
            @RequestHeader("X-User-Email") String email,
            HttpServletRequest http) {
        CustomLogger.logRequest2(
                "WRONG_QUIZ_GET",
                "/api/quiz/wrong",
                "GET",
                email,
                null,
                http
        );
        List<WrongQuizDto> wrongQuizzes = wrongQuizService.getWrongQuizzesByEmail(email);
        return ResponseEntity.ok(wrongQuizzes);
    }

    @PatchMapping("/{summaryId}")
    public ResponseEntity<Void> updateWrongQuiz(
            @RequestHeader("X-User-Email") String email,
            @PathVariable Long summaryId,
            @RequestBody WrongQuizDto request,
            HttpServletRequest http) {
        wrongQuizService.updateWrongQuiz(email, summaryId, request.getStatus());
        
        CustomLogger.logRequest2(
                "WRONG_QUIZ_UPDATE",
                "/api/quiz/wrong/" + summaryId,
                "PATCH",
                email,
                null,
                http
        );
        // ✅ 로그 기록
        return ResponseEntity.noContent().build();
    }

}
