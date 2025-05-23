package edu.goorm.quiz_service.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuizDto {
    private Long summaryId;
    private Integer sentenceIndex;
    private String sentenceText;
    private String translateText;
    private String blankWord;
} 