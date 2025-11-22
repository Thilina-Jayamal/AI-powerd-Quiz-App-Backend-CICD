package com.thilina271.AI.powered.Quiz.app.dto;

import lombok.Data;

@Data
public class AnalyticsSummaryDTO {
    private int quizzesCreated;
    private int quizzesAttempted;
    private double averageScore;
    private int sharedQuizzes;
}
