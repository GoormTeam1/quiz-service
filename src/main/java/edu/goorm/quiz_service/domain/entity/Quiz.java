package edu.goorm.quiz_service.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "quiz")
@Getter
@NoArgsConstructor
public class Quiz {
    
    @EmbeddedId
    private QuizId id;
    
    @Column(name = "sentence_text", length = 1000)
    private String sentenceText;
    
    @Column(name = "translate_text", length = 1000)
    private String translateText;
    
    @Column(name = "blank_word", length = 45)
    private String blankWord;
}
