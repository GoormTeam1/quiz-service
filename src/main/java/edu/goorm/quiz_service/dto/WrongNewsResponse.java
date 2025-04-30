package edu.goorm.quiz_service.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * 사용자의 틀린 문제 목록을 반환하기 위한 DTO
 */
@Getter
@Builder
public class WrongNewsResponse {
    /** 사용자 이메일 */
    private String email;
    
    /** 틀린 문제 목록 */
    private List<WrongNewsDetail> wrongNewsList;

    /**
     * 틀린 문제의 상세 정보를 담는 내부 클래스
     */
    @Getter
    @Builder
    public static class WrongNewsDetail {
        /** 뉴스 ID */
        private Long newsId;
        
        /** 뉴스 제목 */
        private String title;
        
        /** 뉴스 요약 */
        private String summary;
    }
} 