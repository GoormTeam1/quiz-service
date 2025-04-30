package edu.goorm.quiz_service.repository;

import edu.goorm.quiz_service.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 사용자 정보에 접근하는 레포지토리
 */
public interface UsersRepository extends JpaRepository<Users, String> {
} 