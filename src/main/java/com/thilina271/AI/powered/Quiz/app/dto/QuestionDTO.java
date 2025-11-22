package com.thilina271.AI.powered.Quiz.app.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionDTO {
    private int id;
    private String question;
    private List<String> options;
}
