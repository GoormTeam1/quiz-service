package edu.goorm.quiz_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 기사 정보를 전달하기 위한 DTO 클래스 
 * 엔티티와 클라이언트 간의 데이터 전송에 사용됨
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsDto {
    /** 기사 ID */
    private Long id;
    
    /** 기사 제목 */
    private String title;
    
    /** 기사 원문 내용 */
    private String fullText;
    
    /** 기사 출처 */
    private String sourceLink;

    /** 기사 이미지 URL */
    private String image;
    
    /** 기사 카테고리 */
    private String category;
    
    /** 기사 발행 일시 */
    private LocalDateTime publishedAt;
    
    /** 기사 생성 일시 */
    private LocalDateTime createAt;

}
