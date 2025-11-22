package com.thilina271.AI.powered.Quiz.app.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QuizShareDetailsDTO {
    private int id;
    private String title;
    private String email;
    private LocalDateTime sharedAt;
    private int quizId;
}
