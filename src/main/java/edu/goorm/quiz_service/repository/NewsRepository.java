package edu.goorm.quiz_service.repository;

import edu.goorm.quiz_service.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
} 