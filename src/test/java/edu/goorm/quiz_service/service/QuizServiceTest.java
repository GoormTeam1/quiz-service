package edu.goorm.quiz_service.service;

import edu.goorm.quiz_service.dto.ProblemResponse;
import edu.goorm.quiz_service.entity.News;
import edu.goorm.quiz_service.entity.Sentence;
import edu.goorm.quiz_service.repository.NewsRepository;
import edu.goorm.quiz_service.repository.SentenceRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class QuizServiceTest {

    @InjectMocks
    private QuizService quizService;

    @Mock
    private NewsRepository newsRepository;

    @Mock
    private SentenceRepository sentenceRepository;

    @Test
    @DisplayName("뉴스 ID로 퀴즈를 조회하면 문제 목록이 반환된다")
    void getQuizByNewsId_Success() {
        // given
        Long newsId = 1L;
        News news = createNews(newsId);
        Sentence sentence1 = createSentence(1L, news, "삼성전자는 새로운 스마트폰을 발표했다.", 
            "삼성전자는 새로운 ___을 발표했다.", "스마트폰");
        Sentence sentence2 = createSentence(2L, news, "이번 제품은 AI 기능이 강화되었다.", 
            "이번 제품은 AI 기능이 ___되었다.", "강화");

        given(newsRepository.findById(newsId)).willReturn(Optional.of(news));
        given(sentenceRepository.findByNewsOrderBySentenceIdAsc(news))
            .willReturn(Arrays.asList(sentence1, sentence2));

        // when
        ProblemResponse response = quizService.getQuizByNewsId(newsId);

        // then
        assertThat(response.getNewsId()).isEqualTo(newsId);
        assertThat(response.getTitle()).isEqualTo("테스트 뉴스");
        assertThat(response.getProblems()).hasSize(2);
        assertThat(response.getProblems().get(0).getOriginal())
            .isEqualTo("삼성전자는 새로운 스마트폰을 발표했다.");
        assertThat(response.getProblems().get(0).getQuestion())
            .isEqualTo("삼성전자는 새로운 ___을 발표했다.");
        assertThat(response.getProblems().get(0).getAnswer())
            .isEqualTo("스마트폰");

        verify(newsRepository).findById(newsId);
        verify(sentenceRepository).findByNewsOrderBySentenceIdAsc(news);
    }

    @Test
    @DisplayName("존재하지 않는 뉴스 ID로 조회하면 예외가 발생한다")
    void getQuizByNewsId_NewsNotFound() {
        // given
        Long newsId = 999L;
        given(newsRepository.findById(newsId)).willReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> quizService.getQuizByNewsId(newsId))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("News not found");
    }

    private News createNews(Long newsId) {
        News news = new News();
        setField(news, "newsId", newsId);
        setField(news, "title", "테스트 뉴스");
        return news;
    }

    private Sentence createSentence(Long sentenceId, News news, String sentenceText, 
            String blankText, String blankWord) {
        Sentence sentence = new Sentence();
        setField(sentence, "sentenceId", sentenceId);
        setField(sentence, "news", news);
        setField(sentence, "sentenceText", sentenceText);
        setField(sentence, "blankText", blankText);
        setField(sentence, "blankWord", blankWord);
        return sentence;
    }

    private void setField(Object target, String fieldName, Object value) {
        try {
            var field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
} 