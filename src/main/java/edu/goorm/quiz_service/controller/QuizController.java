package edu.goorm.quiz_service.controller;

import edu.goorm.quiz_service.dto.QuizDto;
import edu.goorm.quiz_service.service.QuizService;
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
     * 뉴스 ID로 퀴즈를 조회하는 API
     *
     * @param newsId 조회할 뉴스 ID
     * @return 퀴즈 목록
     */
    @GetMapping("/{newsId}")
    public ResponseEntity<List<QuizDto>> getQuizzesByNewsId(@PathVariable Long newsId) {
        return ResponseEntity.ok(quizService.getQuizzesByNewsId(newsId));
    }
} 