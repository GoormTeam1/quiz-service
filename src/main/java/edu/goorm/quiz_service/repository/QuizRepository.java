package edu.goorm.quiz_service.repository;

import edu.goorm.quiz_service.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.goorm.quiz_service.entity.QuizId;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, QuizId> {
    List<Quiz> findByIdNewsId(Long newsId);
} 