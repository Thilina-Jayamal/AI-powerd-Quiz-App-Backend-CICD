package com.thilina271.AI.powered.Quiz.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String option5;
    private String answer;

    @ManyToOne
    @JoinColumn(name = "quiz_id",nullable = false)
    @JsonIgnore
    private Quiz quiz;

}
