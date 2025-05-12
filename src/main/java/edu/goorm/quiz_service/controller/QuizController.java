package edu.goorm.quiz_service.controller;

import edu.goorm.quiz_service.dto.QuizDto;
import edu.goorm.quiz_service.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

/**
 * 퀴즈 관련 API를 제공하는 컨트롤러
 * 문제 조회, 틀린 문제 등록 및 조회 기능 제공
 */
@Tag(name = "Quiz API", description = "퀴즈 관련 API")
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
    @Operation(summary = "뉴스 요약 ID로 퀴즈 조회", description = "특정 뉴스 요약 ID에 해당하는 퀴즈 목록을 조회합니다.")
    @GetMapping("/{summaryId}")
    public ResponseEntity<List<QuizDto>> getQuizBySummaryId(
            @Parameter(description = "뉴스 요약 ID", required = true) @PathVariable Long summaryId) {
        return ResponseEntity.ok(quizService.getQuizBySummaryId(summaryId));
    }
} 