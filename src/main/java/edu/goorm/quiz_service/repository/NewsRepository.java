package edu.goorm.quiz_service.repository;

import edu.goorm.quiz_service.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 뉴스 정보에 접근하는 레포지토리
 */
public interface NewsRepository extends JpaRepository<News, Long> {
    
    /**
     * 제목으로 뉴스를 검색하는 메서드
     *
     * @param title 검색할 제목 (부분 일치)
     * @return 검색된 뉴스 목록
     */
    List<News> findByTitleContaining(String title);
    
    /**
     * 소스별로 뉴스를 검색하는 메서드
     *
     * @param source 검색할 소스
     * @param pageable 페이징 정보
     * @return 검색된 뉴스 페이지
     */
    Page<News> findBySource(String source, Pageable pageable);
    
    /**
     * 제목이나 전체 텍스트에서 키워드를 검색하는 메서드
     *
     * @param keyword 검색할 키워드
     * @return 검색된 뉴스 목록
     */
    @Query("SELECT n FROM News n WHERE n.title LIKE %:keyword% OR n.fullText LIKE %:keyword%")
    List<News> searchByKeyword(@Param("keyword") String keyword);
    
    /**
     * 특정 기간 내의 뉴스를 검색하는 메서드 (향후 확장을 위해 준비)
     */
    // @Query("SELECT n FROM News n WHERE n.createdAt BETWEEN :startDate AND :endDate")
    // List<News> findByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
} 