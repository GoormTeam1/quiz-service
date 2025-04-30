package edu.goorm.quiz_service.repository;

import edu.goorm.quiz_service.entity.WrongNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * 틀린 문제 정보에 접근하는 레포지토리
 */
public interface WrongNewsRepository extends JpaRepository<WrongNews, WrongNews.WrongNewsId> {
    
    /**
     * 사용자의 틀린 문제 목록을 조회하는 메서드
     */
    @Query("SELECT w FROM WrongNews w WHERE w.email = :email")
    List<WrongNews> findByEmail(@Param("email") String email);
    
    /**
     * 특정 사용자가 해당 뉴스를 틀린 문제로 등록했는지 확인하는 메서드
     */
    boolean existsByEmailAndNewsId(String email, Long newsId);
    
    /**
     * 사용자의 틀린 문제 목록을 삭제하는 메서드
     */
    void deleteByEmail(String email);
    
    /**
     * 특정 뉴스의 틀린 문제 기록을 삭제하는 메서드
     */
    void deleteByEmailAndNewsId(String email, Long newsId);
    
    /**
     * 특정 사용자의 특정 뉴스에 대한 틀린 문제를 조회하는 메서드
     */
    Optional<WrongNews> findByEmailAndNewsId(String email, Long newsId);
} 