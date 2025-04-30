package edu.goorm.quiz_service.controller;

import edu.goorm.quiz_service.dto.ProblemResponse;
import edu.goorm.quiz_service.dto.WrongNewsResponse;
import edu.goorm.quiz_service.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * @return 문제 목록이 포함된 응답
     */
    @GetMapping("/news/{newsId}")
    public ResponseEntity<ProblemResponse> getQuizByNewsId(@PathVariable Long newsId) {
        return ResponseEntity.ok(quizService.getQuizByNewsId(newsId));
    }

    /**
     * 틀린 문제를 등록하는 API
     *
     * @param email 사용자 이메일 (헤더에서 추출)
     * @param newsId 틀린 문제의 뉴스 ID
     * @return 성공 시 200 OK
     */
    @PostMapping("/wrong/{newsId}")
    public ResponseEntity<Void> markNewsAsWrong(
            @RequestHeader("X-User-Email") String email,
            @PathVariable Long newsId) {
        quizService.markNewsAsWrong(email, newsId);
        return ResponseEntity.ok().build();
    }

    /**
     * 사용자의 틀린 문제 목록을 조회하는 API
     *
     * @param email 사용자 이메일 (헤더에서 추출)
     * @return 틀린 문제 목록이 포함된 응답
     */
    @GetMapping("/wrong")
    public ResponseEntity<WrongNewsResponse> getWrongNewsList(
            @RequestHeader("X-User-Email") String email) {
        return ResponseEntity.ok(quizService.getWrongNewsList(email));
    }
} 