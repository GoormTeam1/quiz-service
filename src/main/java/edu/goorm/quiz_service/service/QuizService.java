package edu.goorm.quiz_service.service;

import edu.goorm.quiz_service.dto.ProblemResponse;
import edu.goorm.quiz_service.entity.News;
import edu.goorm.quiz_service.entity.Sentence;
import edu.goorm.quiz_service.repository.NewsRepository;
import edu.goorm.quiz_service.repository.SentenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuizService {
    
    private final NewsRepository newsRepository;
    private final SentenceRepository sentenceRepository;
    
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
} 