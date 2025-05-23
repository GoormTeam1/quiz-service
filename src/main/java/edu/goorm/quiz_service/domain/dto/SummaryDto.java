package edu.goorm.quiz_service.domain.dto;

import lombok.Builder;
import lombok.Getter;


/**
 * 요약 정보를 전달하는 DTO 클래스
 */
@Getter
@Builder
public class SummaryDto {
    private Long summaryId;
    private Long newsId;
    private String level;
    private String summary;

}
