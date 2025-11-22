package com.thilina271.AI.powered.Quiz.app.mapper;

import com.thilina271.AI.powered.Quiz.app.dto.QuizAttemptDTO;
import com.thilina271.AI.powered.Quiz.app.model.QuizAttempt;
import lombok.Data;

@Data
public class QuizAttemptMapper {
    public static QuizAttemptDTO toDTO(QuizAttempt attempt){
        QuizAttemptDTO quizAttemptDTO = new QuizAttemptDTO();

        quizAttemptDTO.setId(attempt.getId());
        quizAttemptDTO.setQuizId(attempt.getQuiz().getId());
        quizAttemptDTO.setResult(attempt.getResult());
        quizAttemptDTO.setTitle(attempt.getQuiz().getTitle());
        quizAttemptDTO.setAttemptAt(attempt.getTimestamp());

        return quizAttemptDTO;

    }
}
