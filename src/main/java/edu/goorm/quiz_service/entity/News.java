package edu.goorm.quiz_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "news")
@Getter
@NoArgsConstructor
public class News {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsId;
    
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String fullText;
    
    @Column(columnDefinition = "TEXT")
    private String summary;
    
    private String source;
    
    private String image;
} 