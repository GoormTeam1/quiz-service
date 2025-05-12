package edu.goorm.quiz_service.service;

import edu.goorm.quiz_service.dto.WrongQuizDto;
import edu.goorm.quiz_service.entity.WrongQuiz;
import edu.goorm.quiz_service.repository.WrongQuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class WrongQuizService {

    private final WrongQuizRepository wrongQuizRepository;

    public void saveWrongQuiz(String userEmail, Long summaryId) {
        boolean exists = wrongQuizRepository
            .findByUserEmailAndSummaryId(userEmail, summaryId)
            .isPresent();

        if (!exists) {
            WrongQuiz quiz = WrongQuiz.builder()
                .userEmail(userEmail)
                .summaryId(summaryId)
                .build();

            wrongQuizRepository.save(quiz);
        }
    }

    @Transactional
    public void deleteWrongQuiz(String userEmail, Long summaryId) {
        wrongQuizRepository.deleteByUserEmailAndSummaryId(userEmail, summaryId);
    }

    public List<WrongQuizDto> getWrongQuizzesByEmail(String email) {
        List<WrongQuiz> wrongList = wrongQuizRepository.findByUserEmail(email);
        return wrongList.stream()
            .map(wq -> new WrongQuizDto(wq.getUserEmail(), wq.getSummaryId()))
            .collect(Collectors.toList());
    }
    
}
