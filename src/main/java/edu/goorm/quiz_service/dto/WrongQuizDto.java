package edu.goorm.quiz_service.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WrongQuizDto {
    private String userEmail;
    private Long summaryId;
    private String status;
    private String image;
    private String level;
    private LocalDate createdAt;
    private LocalDate publishAt;
    private String title;
    private String cateogry;

    public WrongQuizDto(String userEmail, Long summaryId, String status, String image, String level, LocalDate createdAt, LocalDate publishAt, String title, String category) {
        this.userEmail = userEmail;
        this.summaryId = summaryId;
        this.status = status;
        this.image = image;
        this.level = level;
        this.createdAt = createdAt;
        this.publishAt = publishAt;
        this.title = title;
        this.cateogry = category;
    }
}