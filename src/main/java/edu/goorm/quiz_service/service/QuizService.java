package edu.goorm.quiz_service.service;

import edu.goorm.quiz_service.dto.ProblemResponse;
import edu.goorm.quiz_service.dto.WrongNewsResponse;
import edu.goorm.quiz_service.entity.News;
import edu.goorm.quiz_service.entity.Sentence;
import edu.goorm.quiz_service.entity.WrongNews;
import edu.goorm.quiz_service.repository.NewsRepository;
import edu.goorm.quiz_service.repository.SentenceRepository;
import edu.goorm.quiz_service.repository.WrongNewsRepository;
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
    
    private final NewsRepository newsRepository;
    private final SentenceRepository sentenceRepository;
    private final WrongNewsRepository wrongNewsRepository;
    
    /**
     * 뉴스 ID로 퀴즈를 조회하는 메서드
     * 해당 뉴스의 문장들을 기반으로 빈칸 채우기 문제를 생성
     *
     * @param newsId 조회할 뉴스 ID
     * @return 문제 목록이 포함된 응답 객체
     * @throws IllegalArgumentException 뉴스가 존재하지 않는 경우
     */
    public ProblemResponse getQuizByNewsId(Long newsId) {
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new IllegalArgumentException("News not found"));
                
        List<Sentence> sentences = sentenceRepository.findByNewsOrderBySentenceIdAsc(news);
        
        List<ProblemResponse.Problem> problems = sentences.stream()
                .map(sentence -> ProblemResponse.Problem.builder()
                        .sentenceId(sentence.getSentenceId())
                        .original(sentence.getSentenceText())
                        .question(sentence.getBlankText())
                        .answer(sentence.getBlankWord())
                        .build())
                .collect(Collectors.toList());
                
        return ProblemResponse.builder()
                .newsId(news.getNewsId())
                .title(news.getTitle())
                .problems(problems)
                .build();
    }

    /**
     * 사용자가 틀린 문제를 등록하는 메서드
     * 이미 등록된 문제는 중복 저장되지 않음
     *
     * @param email 사용자 이메일
     * @param newsId 틀린 문제의 뉴스 ID
     * @throws IllegalArgumentException 뉴스가 존재하지 않는 경우
     */
    @Transactional
    public void markNewsAsWrong(String email, Long newsId) {
        if (wrongNewsRepository.existsByEmailAndNewsNewsId(email, newsId)) {
            return;
        }

        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new IllegalArgumentException("News not found"));

        WrongNews wrongNews = new WrongNews();
        setField(wrongNews, "email", email);
        setField(wrongNews, "news", news);
        
        wrongNewsRepository.save(wrongNews);
    }

    /**
     * 사용자의 틀린 문제 목록을 조회하는 메서드
     *
     * @param email 사용자 이메일
     * @return 틀린 문제 목록이 포함된 응답 객체
     */
    public WrongNewsResponse getWrongNewsList(String email) {
        List<WrongNews> wrongNewsList = wrongNewsRepository.findByEmailWithNews(email);
        
        List<WrongNewsResponse.WrongNewsDetail> details = wrongNewsList.stream()
                .map(wrongNews -> WrongNewsResponse.WrongNewsDetail.builder()
                        .newsId(wrongNews.getNews().getNewsId())
                        .title(wrongNews.getNews().getTitle())
                        .summary(wrongNews.getNews().getSummary())
                        .build())
                .collect(Collectors.toList());
                
        return WrongNewsResponse.builder()
                .email(email)
                .wrongNewsList(details)
                .build();
    }

    /**
     * 리플렉션을 사용하여 private 필드에 값을 설정하는 유틸리티 메서드
     */
    private void setField(Object target, String fieldName, Object value) {
        try {
            var field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
} 