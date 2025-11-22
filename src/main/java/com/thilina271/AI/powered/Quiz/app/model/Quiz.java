package com.thilina271.AI.powered.Quiz.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Question> questions;
    private String title;

    @OneToMany(mappedBy = "quiz",cascade = CascadeType.ALL)
    private List<QuizAttempt> attempts;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "quiz",cascade = CascadeType.ALL)
    private List<QuizShare> quizShares;
}
