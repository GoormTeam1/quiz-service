package edu.goorm.quiz_service.service;

import edu.goorm.quiz_service.dto.WrongQuizDto;
import edu.goorm.quiz_service.entity.WrongQuiz;
import edu.goorm.quiz_service.client.NewsClient;
import edu.goorm.quiz_service.dto.NewsDto;
import edu.goorm.quiz_service.dto.SummaryDto;
import edu.goorm.quiz_service.repository.WrongQuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WrongQuizService {

    private final WrongQuizRepository wrongQuizRepository;
    private final NewsClient newsClient;

    public void saveWrongQuiz(String userEmail, Long summaryId) {
        boolean exists = wrongQuizRepository
                .findByUserEmailAndSummaryId(userEmail, summaryId)
                .isPresent();

        if (!exists) {
            WrongQuiz quiz = WrongQuiz.builder()
                    .userEmail(userEmail)
                    .summaryId(summaryId)
                    .status("learning")
                    .build();

            wrongQuizRepository.save(quiz);
        }
    }


    public List<WrongQuizDto> getWrongQuizzesByEmail(String email) {
        List<WrongQuiz> wrongList = wrongQuizRepository.findByUserEmail(email);
    
        List<CompletableFuture<WrongQuizDto>> futures = wrongList.stream()
            .map(wq -> CompletableFuture.supplyAsync(() -> {
                SummaryDto summaryDto = newsClient.getSummaryBySummaryId(email, wq.getSummaryId());
                NewsDto newsDto = summaryDto != null
                    ? newsClient.getNewsById(email, summaryDto.getNewsId())
                    : null;
    
                return new WrongQuizDto(
                    wq.getUserEmail(),
                    wq.getSummaryId(),
                    wq.getStatus(),
                    newsDto != null ? newsDto.getImage() : null,
                    summaryDto != null ? summaryDto.getLevel() : null,
                    wq.getCreatedAt().toLocalDate(),
                    newsDto != null && newsDto.getPublishedAt() != null ? newsDto.getPublishedAt().toLocalDate() : null,
                    newsDto != null ? newsDto.getTitle() : null,
                    newsDto != null ? newsDto.getCategory() : null
                );
            }))
            .collect(Collectors.toList());
    
        // 모든 Future 결과 수집
        return futures.stream()
            .map(CompletableFuture::join)
            .collect(Collectors.toList());
    }

    @Transactional
    public void deleteWrongQuiz(String email, Long summaryId) {
        wrongQuizRepository.deleteByUserEmailAndSummaryId(email, summaryId);
    }

    @Transactional
    public void updateWrongQuiz(String email, Long summaryId, String status) {
        WrongQuiz wrongQuiz = wrongQuizRepository
                .findByUserEmailAndSummaryId(email, summaryId)
                .orElseThrow(() -> new IllegalArgumentException("Wrong quiz not found"));

        wrongQuiz.setStatus(status);
        wrongQuizRepository.save(wrongQuiz);
    }
}
