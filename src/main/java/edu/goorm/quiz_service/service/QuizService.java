package edu.goorm.quiz_service.service;

import edu.goorm.quiz_service.dto.QuizDto;
import edu.goorm.quiz_service.entity.Quiz;
import edu.goorm.quiz_service.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 퀴즈 관련 비즈니스 로직을 처리하는 서비스
 * 뉴스 기사 기반의 빈칸 채우기 문제 생성 및 틀린 문제 관리 기능 제공
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuizService {
    
    private final QuizRepository quizRepository;
    
    /**
     * 뉴스 ID로 퀴즈를 조회하는 메서드
     *
     * @param summaryId 조회할 뉴스 요약 ID
     * @return 퀴즈 목록
     */
    public List<QuizDto> getQuizBySummaryId(Long summaryId) {
        log.info("Fetching quizzes for summaryId: {}", summaryId);
        List<Quiz> quizzes = quizRepository.findByIdSummaryId(summaryId);
        log.info("Found {} quizzes", quizzes.size());
        
        return quizzes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    private QuizDto convertToDto(Quiz quiz) {
        QuizDto dto = new QuizDto();
        dto.setSummaryId(quiz.getId().getSummaryId());
        dto.setSentenceIndex(quiz.getId().getSentenceIndex());
        dto.setSentenceText(quiz.getSentenceText());
        dto.setTranslateText(quiz.getTranslateText());
        dto.setBlankText(quiz.getBlankText());
        dto.setBlankWord(quiz.getBlankWord());
        return dto;
    }
} 