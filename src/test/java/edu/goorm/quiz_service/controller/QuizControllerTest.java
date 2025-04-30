package edu.goorm.quiz_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.goorm.quiz_service.dto.ProblemResponse;
import edu.goorm.quiz_service.service.QuizService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QuizController.class)
class QuizControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuizService quizService;

    @Test
    @DisplayName("뉴스 ID로 퀴즈를 조회하면 성공적으로 응답한다")
    void getQuizByNewsId() throws Exception {
        // given
        Long newsId = 1L;
        ProblemResponse response = ProblemResponse.builder()
                .newsId(newsId)
                .title("테스트 뉴스")
                .problems(Arrays.asList(
                        ProblemResponse.Problem.builder()
                                .sentenceId(1L)
                                .original("삼성전자는 새로운 스마트폰을 발표했다.")
                                .question("삼성전자는 새로운 ___을 발표했다.")
                                .answer("스마트폰")
                                .build()
                ))
                .build();

        given(quizService.getQuizByNewsId(newsId)).willReturn(response);

        // when & then
        mockMvc.perform(get("/api/quiz/news/{newsId}", newsId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.newsId").value(newsId))
                .andExpect(jsonPath("$.title").value("테스트 뉴스"))
                .andExpect(jsonPath("$.problems[0].sentenceId").value(1))
                .andExpect(jsonPath("$.problems[0].original").value("삼성전자는 새로운 스마트폰을 발표했다."))
                .andExpect(jsonPath("$.problems[0].question").value("삼성전자는 새로운 ___을 발표했다."))
                .andExpect(jsonPath("$.problems[0].answer").value("스마트폰"));
    }

    @Test
    @DisplayName("존재하지 않는 뉴스 ID로 조회하면 404 응답을 반환한다")
    void getQuizByNewsId_NotFound() throws Exception {
        // given
        Long newsId = 999L;
        given(quizService.getQuizByNewsId(newsId))
                .willThrow(new IllegalArgumentException("News not found"));

        // when & then
        mockMvc.perform(get("/api/quiz/news/{newsId}", newsId))
                .andExpect(status().isNotFound());
    }
} 