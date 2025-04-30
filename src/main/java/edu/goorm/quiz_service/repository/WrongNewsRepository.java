package edu.goorm.quiz_service.repository;

import edu.goorm.quiz_service.entity.WrongNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 틀린 문제 정보에 접근하는 레포지토리
 */
public interface WrongNewsRepository extends JpaRepository<WrongNews, Long> {
    
    /**
     * 사용자의 틀린 문제 목록을 조회하는 메서드
     * 성능 최적화를 위해 뉴스 정보를 함께 조회 (FETCH JOIN)
     *
     * @param email 사용자 이메일
     * @return 틀린 문제 목록
     */
    @Query("SELECT w FROM WrongNews w JOIN FETCH w.news WHERE w.email = :email")
    List<WrongNews> findByEmailWithNews(@Param("email") String email);
    
    /**
     * 특정 사용자가 해당 뉴스를 틀린 문제로 등록했는지 확인하는 메서드
     *
     * @param email 사용자 이메일
     * @param newsId 뉴스 ID
     * @return 등록 여부
     */
    boolean existsByEmailAndNewsNewsId(String email, Long newsId);
} 