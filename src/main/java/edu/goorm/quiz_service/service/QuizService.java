package edu.goorm.quiz_service.service;

import edu.goorm.quiz_service.dto.QuizDto;
import edu.goorm.quiz_service.entity.Quiz;
import edu.goorm.quiz_service.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 퀴즈 관련 비즈니스 로직을 처리하는 서비스
 * 뉴스 기사 기반의 빈칸 채우기 문제 생성 및 틀린 문제 관리 기능 제공
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuizService {
    
    private final QuizRepository quizRepository;
    
    /**
     * 뉴스 ID로 퀴즈를 조회하는 메서드
     *
     * @param newsId 조회할 뉴스 ID
     * @return 퀴즈 목록
     */
    public List<QuizDto> getQuizzesByNewsId(Long newsId) {
        return quizRepository.findByIdNewsId(newsId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    private QuizDto convertToDto(Quiz quiz) {
        QuizDto dto = new QuizDto();
        dto.setNewsId(quiz.getId().getNewsId());
        dto.setSentenceIndex(quiz.getId().getSentenceIndex());
        dto.setSentenceText(quiz.getSentenceText());
        dto.setTranslateText(quiz.getTranslateText());
        dto.setBlankText(quiz.getBlankText());
        dto.setBlankWord(quiz.getBlankWord());
        return dto;
    }
} 