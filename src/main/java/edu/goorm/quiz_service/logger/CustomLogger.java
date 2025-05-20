package edu.goorm.quiz_service.logger;

import edu.goorm.quiz_service.controller.QuizController;
import edu.goorm.quiz_service.controller.WrongQuizController;
import edu.goorm.quiz_service.entity.WrongQuiz;
import edu.goorm.quiz_service.util.CustomIpUtil;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomLogger {
    private static final Logger logger1 = LogManager.getLogger(QuizController.class);
    private static final Logger logger2 = LogManager.getLogger(WrongQuizController.class);

    public static void logRequest1(
        String logType,
        String url,
        String method,
        String userId,
        String payload,
        HttpServletRequest request
    ) {
        logger1.info(String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s",
            logType,                          // ex: USER_LOGIN, USER_UPDATE_LEVEL
            LocalDateTime.now(),             // 로그 시간
            url,                             // 요청 URL
            method,                          // GET, POST 등
            userId != null ? userId : "-",   // 로그인 전엔 null일 수 있음
            payload != null ? payload : "-", // 요청 핵심 내용
            CustomIpUtil.getClientIp(request),
            request.getHeader("User-Agent")
        ));
    }

    public static void logRequest2(
        String logType,
        String url,
        String method,
        String userId,
        String payload,
        HttpServletRequest request
    ) {
        logger2.info(String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s",
            logType,                          // ex: USER_LOGIN, USER_UPDATE_LEVEL
            LocalDateTime.now(),             // 로그 시간
            url,                             // 요청 URL
            method,                          // GET, POST 등
            userId != null ? userId : "-",   // 로그인 전엔 null일 수 있음
            payload != null ? payload : "-", // 요청 핵심 내용
            CustomIpUtil.getClientIp(request),
            request.getHeader("User-Agent")
        ));
    }

}