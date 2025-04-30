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
    @Column(name = "news_id")
    private Long newsId;
    
    @Column(length = 45)
    private String title;
    
    @Column(name = "full_text", columnDefinition = "TEXT(65535)")
    private String fullText;
    
    @Column(columnDefinition = "TEXT(10000)")
    private String summary;
    
    @Column(length = 45)
    private String source;
    
    @Column(length = 100)
    private String image;
} 