package edu.goorm.quiz_service.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ProblemResponse {
    private Long newsId;
    private String title;
    private List<Problem> problems;

    @Getter
    @Builder
    public static class Problem {
        private Long sentenceId;
        private String original;
        private String question;
        private String answer;
    }
} 