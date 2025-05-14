package edu.goorm.quiz_service.controller;

import edu.goorm.quiz_service.dto.WrongQuizDto;
import edu.goorm.quiz_service.service.WrongQuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz/wrong")
@RequiredArgsConstructor
public class WrongQuizController {

    private final WrongQuizService wrongQuizService;

    @PostMapping
    public ResponseEntity<Void> saveWrongQuiz(
            @RequestHeader("X-User-Email") String email,
            @RequestBody WrongQuizDto request) {
        wrongQuizService.saveWrongQuiz(email, request.getSummaryId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{summaryId}")
    public ResponseEntity<Void> deleteWrongQuiz(
            @RequestHeader("X-User-Email") String email,
            @PathVariable Long summaryId) {
        wrongQuizService.deleteWrongQuiz(email, summaryId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<WrongQuizDto>> getWrongQuizzes(
            @RequestHeader("X-User-Email") String email) {
        List<WrongQuizDto> wrongQuizzes = wrongQuizService.getWrongQuizzesByEmail(email);
        return ResponseEntity.ok(wrongQuizzes);
    }

    @PatchMapping("/{summaryId}")
    public ResponseEntity<Void> updateWrongQuiz(
            @RequestHeader("X-User-Email") String email,
            @PathVariable Long summaryId,
            @RequestBody WrongQuizDto request) {
        wrongQuizService.updateWrongQuiz(email, summaryId, request.getStatus());
        return ResponseEntity.noContent().build();
    }

}
