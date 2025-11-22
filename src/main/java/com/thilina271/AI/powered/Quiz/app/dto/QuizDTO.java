package com.thilina271.AI.powered.Quiz.app.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuizDTO {
    private int id;
    private List<QuestionDTO> questions;
    private String title;
}
