package com.thilina271.AI.powered.Quiz.app.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QuizAttemptDTO {
    private int id;
    private float result;
    private LocalDateTime attemptAt;
    private int quizId;
    private String title;
}
