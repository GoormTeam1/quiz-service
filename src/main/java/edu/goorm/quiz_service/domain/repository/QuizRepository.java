package edu.goorm.quiz_service.domain.repository;

import edu.goorm.quiz_service.domain.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.goorm.quiz_service.domain.entity.QuizId;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, QuizId> {

    List<Quiz> findByIdSummaryId(Long summaryId);
}