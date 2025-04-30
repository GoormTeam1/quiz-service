package edu.goorm.quiz_service.dto;

import edu.goorm.quiz_service.entity.News;
import edu.goorm.quiz_service.entity.Users;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 사용자의 틀린 문제 목록을 반환하기 위한 DTO
 */
@Getter
public class WrongNewsResponse {
    /** 사용자 이메일 */
    private final String email;
    
    /** 사용자 이름 */
    private final String userName;
    
    /** 사용자 레벨 */
    private final String level;
    
    /** 틀린 문제 목록 */
    private final List<WrongNewsDetail> wrongNewsList;

    @Builder
    private WrongNewsResponse(String email, String userName, String level, List<WrongNewsDetail> wrongNewsList) {
        this.email = email;
        this.userName = userName;
        this.level = level;
        this.wrongNewsList = wrongNewsList;
    }

    public static WrongNewsResponse from(Users user, List<News> newsList) {
        List<WrongNewsDetail> details = newsList.stream()
                .map(news -> WrongNewsDetail.builder()
                        .newsId(news.getNewsId())
                        .title(news.getTitle())
                        .summary(news.getSummary())
                        .source(news.getSource())
                        .image(news.getImage())
                        .build())
                .collect(Collectors.toList());

        return WrongNewsResponse.builder()
                .email(user.getEmail())
                .userName(user.getUserName())
                .level(user.getLevel())
                .wrongNewsList(details)
                .build();
    }

    /**
     * 틀린 문제의 상세 정보를 담는 내부 클래스
     */
    @Getter
    @Builder
    public static class WrongNewsDetail {
        /** 뉴스 ID */
        private final Long newsId;
        
        /** 뉴스 제목 */
        private final String title;
        
        /** 뉴스 요약 */
        private final String summary;
        
        /** 뉴스 출처 */
        private final String source;
        
        /** 뉴스 이미지 */
        private final String image;
    }
} 