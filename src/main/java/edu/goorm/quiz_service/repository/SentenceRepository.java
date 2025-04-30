package edu.goorm.quiz_service.repository;

import edu.goorm.quiz_service.entity.News;
import edu.goorm.quiz_service.entity.Sentence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SentenceRepository extends JpaRepository<Sentence, Long> {
    List<Sentence> findByNewsOrderBySentenceIdAsc(News news);
} 