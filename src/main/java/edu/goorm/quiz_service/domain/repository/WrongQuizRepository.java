package edu.goorm.quiz_service.domain.repository;

import edu.goorm.quiz_service.domain.entity.WrongQuiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface WrongQuizRepository extends JpaRepository<WrongQuiz, Long> {
    Optional<WrongQuiz> findByUserEmailAndSummaryId(String userEmail, Long summaryId);
    void deleteByUserEmailAndSummaryId(String userEmail, Long summaryId);
    List<WrongQuiz> findByUserEmail(String email);
    
}
