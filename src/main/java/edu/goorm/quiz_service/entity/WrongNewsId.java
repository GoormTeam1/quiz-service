package edu.goorm.quiz_service.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class WrongNewsId implements Serializable {
    private String email;
    private Long news; // News의 기본키 타입과 일치시켜야 함
}
