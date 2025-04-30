package edu.goorm.quiz_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sentence")
@Getter
@NoArgsConstructor
public class Sentence {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sentenceId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id")
    private News news;
    
    private String sentenceText;
    
    private String blankText;
    
    private String blankWord;
}

