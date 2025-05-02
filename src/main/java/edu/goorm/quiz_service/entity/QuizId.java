package edu.goorm.quiz_service.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;
import java.io.Serializable;
import java.util.Objects;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Embeddable
@Getter
@NoArgsConstructor
public class QuizId implements Serializable {

    @Column(name = "news_id")
    private Long newsId;

    @Column(name = "sentence_index")
    private Integer sentenceIndex;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuizId)) return false;
        QuizId that = (QuizId) o;
        return Objects.equals(newsId, that.newsId) &&
               Objects.equals(sentenceIndex, that.sentenceIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newsId, sentenceIndex);
    }
}

