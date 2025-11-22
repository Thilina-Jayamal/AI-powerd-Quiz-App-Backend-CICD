package com.thilina271.AI.powered.Quiz.app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GeneratedQuestionDTO {
    @NotBlank
    private String question;
    @NotBlank
    private String option1;
    @NotBlank
    private String option2;
    @NotBlank
    private String option3;
    @NotBlank
    private String option4;
    @NotBlank
    private String option5;
    @NotBlank
    private String answer;
}
