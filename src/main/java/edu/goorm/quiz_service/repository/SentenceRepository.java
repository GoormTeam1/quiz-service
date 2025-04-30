package edu.goorm.quiz_service.repository;

import edu.goorm.quiz_service.entity.News;
import edu.goorm.quiz_service.entity.Sentence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 문장 정보에 접근하는 레포지토리
 */
public interface SentenceRepository extends JpaRepository<Sentence, Long> {
    
    /**
     * 특정 뉴스의 문장들을 순서대로 조회하는 메서드
     *
     * @param news 조회할 뉴스
     * @return 문장 목록 (sentenceId 기준 오름차순)
     */
    List<Sentence> findByNewsOrderBySentenceIdAsc(News news);
    
    /**
     * 특정 뉴스의 문장들을 조회하는 메서드 (뉴스 정보 포함)
     *
     * @param newsId 조회할 뉴스 ID
     * @return 문장 목록
     */
    @Query("SELECT s FROM Sentence s JOIN FETCH s.news WHERE s.news.newsId = :newsId")
    List<Sentence> findByNewsIdWithNews(@Param("newsId") Long newsId);
    
    /**
     * 특정 단어가 정답인 문장들을 검색하는 메서드
     *
     * @param word 검색할 단어
     * @return 문장 목록
     */
    List<Sentence> findByBlankWord(String word);
    
    /**
     * 특정 뉴스의 모든 문장을 삭제하는 메서드
     *
     * @param newsId 삭제할 뉴스 ID
     */
    void deleteByNewsNewsId(Long newsId);
} 