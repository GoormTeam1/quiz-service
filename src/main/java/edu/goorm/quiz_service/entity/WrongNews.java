package edu.goorm.quiz_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@IdClass(WrongNews.WrongNewsId.class)
@Getter
@Setter
@NoArgsConstructor
public class WrongNews {

    @Id
    @Column(name = "email")
    private String email;

    @Id
    @Column(name = "news_id")
    private Long newsId;

    public WrongNews(String email, Long newsId) {
        this.email = email;
        this.newsId = newsId;
    }

    @Embeddable
    @NoArgsConstructor
    @Getter
    public static class WrongNewsId implements Serializable {
        private String email;
        private Long newsId;

        public WrongNewsId(String email, Long newsId) {
            this.email = email;
            this.newsId = newsId;
        }
    }
}
