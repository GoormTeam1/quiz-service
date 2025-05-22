package edu.goorm.quiz_service.consumer;


import edu.goorm.quiz_service.service.QuizBulkInsertBuffer;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.goorm.quiz_service.dto.QuizDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuizKafkaConsumer {
    
    private final QuizBulkInsertBuffer quizBulkInsertBuffer;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "quiz", groupId = "quiz-group")
    public void consume(String message) {
        try {
            QuizDto quizDto = objectMapper.readValue(message, QuizDto.class);
            quizBulkInsertBuffer.add(quizDto);
        } catch (Exception e) {
            // Handle JSON parsing error 
            log.error("Failed to parse message: {}", message, e);
        }
    }
}
