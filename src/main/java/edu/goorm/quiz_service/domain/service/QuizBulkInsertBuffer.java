package edu.goorm.quiz_service.domain.service;

import edu.goorm.quiz_service.domain.dto.QuizDto;
import edu.goorm.quiz_service.domain.entity.Quiz;
import edu.goorm.quiz_service.domain.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class QuizBulkInsertBuffer {
    
    private final List<Quiz> buffer = Collections.synchronizedList(new ArrayList<>());
    private final QuizRepository QuizRepository;
    private static final int BATCH_SIZE = 100; // 배치 사이즈 설정

    public void add(QuizDto dto){
        buffer.add(Quiz.builder()
                .sentenceText(dto.getSentenceText())
                .translateText(dto.getTranslateText())
                .blankWord(dto.getBlankWord())
                .build());
        log.info("Added to buffer: " + dto.getSummaryId());
        if(buffer.size() >= BATCH_SIZE) {
            flush();
        }
    }
    
    @Scheduled(fixedRate = 5000)
    public void pereodicFlush() {
        if (!buffer.isEmpty()) {
            flush();
        }
    }

    @Transactional
    public synchronized void flush() {
        if (buffer.isEmpty()) return;

        List<Quiz> toSave = new ArrayList<>(buffer);
        buffer.clear();
        QuizRepository.saveAll(toSave);
        log.info("Flushed {} quiz items to DB", toSave.size());
    }
}
