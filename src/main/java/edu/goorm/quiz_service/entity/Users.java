package edu.goorm.quiz_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
public class Users {
    
    @Id
    @Column(length = 50)
    private String email;
    
    @Column(name = "user_name", length = 10)
    private String userName;
    
    @Column(length = 50)
    private String password;
    
    @Column(length = 10)
    private String gender;
    
    @Column(name = "birth_date")
    private LocalDate birthDate;
    
    @Column(length = 10)
    private String interest;
    
    @Column(length = 10)
    private String level;
} 