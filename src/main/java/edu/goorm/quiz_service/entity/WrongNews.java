package edu.goorm.quiz_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 사용자가 틀린 뉴스 문제를 저장하는 엔티티
 * Users와 News 테이블의 다대다 관계를 관리
 */
@Entity
@Table(name = "wrong_news")
@Getter
@NoArgsConstructor
public class WrongNews {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * 사용자 이메일 (Users 테이블의 email과 매핑)
     */
    @Column(name = "email")
    private String email;
    
    /**
     * 틀린 문제의 뉴스 정보
     * 지연 로딩을 사용하여 성능 최적화
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id")
    private News news;
} 