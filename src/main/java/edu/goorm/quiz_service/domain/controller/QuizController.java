package edu.goorm.quiz_service.domain.controller;

import edu.goorm.quiz_service.domain.dto.QuizDto;
import edu.goorm.quiz_service.global.logger.CustomLogger;
import edu.goorm.quiz_service.domain.service.QuizService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 퀴즈 관련 API를 제공하는 컨트롤러
 * 문제 조회, 틀린 문제 등록 및 조회 기능 제공
 */
@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    /**
     * 뉴스 요약 ID로 퀴즈를 조회하는 API
     *
     * @param summaryId 조회할 뉴스 요약 ID
     * @return 퀴즈 목록
     */
    @GetMapping("/{summaryId}")
    public ResponseEntity<List<QuizDto>> getQuizBySummaryId(
        @RequestHeader("X-User-Email") String email,
        @PathVariable Long summaryId,
        HttpServletRequest request) {
        long startTime = System.currentTimeMillis();

        List<QuizDto> result = quizService.getQuizBySummaryId(summaryId);

        long endTime = System.currentTimeMillis();
        long durationMs = endTime - startTime;

        CustomLogger.logRequest(
            "QUIZ_GET",
            "/api/quiz/" + summaryId,
            "GET",
            email,
            request,
            200,
            durationMs
        );

        return ResponseEntity.ok(result);
    }
}
