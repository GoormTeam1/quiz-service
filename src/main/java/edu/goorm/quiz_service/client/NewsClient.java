package edu.goorm.quiz_service.client;

import edu.goorm.quiz_service.config.FeignConfig;
import edu.goorm.quiz_service.dto.NewsDto;
import edu.goorm.quiz_service.dto.SummaryDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(
    name = "news-service",
    url = "http://172.16.24.156:8082",
    configuration = FeignConfig.class
)
public interface NewsClient {
    @GetMapping("/api/news/{id}")
    NewsDto getNewsById(@RequestHeader("X-User-Email") String email, @PathVariable Long id);

    @GetMapping("/api/summary/search/{summaryId}")
    SummaryDto getSummaryBySummaryId(@RequestHeader("X-User-Email") String email, @PathVariable Long summaryId);
}
