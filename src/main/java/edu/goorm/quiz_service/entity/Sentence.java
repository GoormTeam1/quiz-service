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
    @Column(name = "sentence_id")
    private Long sentenceId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id")
    private News news;
    
    @Column(name = "sentence_text", length = 200)
    private String sentenceText;
    
    @Column(name = "blank_text", length = 200)
    private String blankText;
    
    @Column(name = "blank_word", length = 45)
    private String blankWord;
}

