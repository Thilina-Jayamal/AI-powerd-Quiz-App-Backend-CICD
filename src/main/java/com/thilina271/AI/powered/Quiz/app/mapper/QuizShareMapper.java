package com.thilina271.AI.powered.Quiz.app.mapper;

import com.thilina271.AI.powered.Quiz.app.dto.QuizShareDetailsDTO;
import com.thilina271.AI.powered.Quiz.app.model.QuizShare;
import lombok.Data;

@Data
public class QuizShareMapper {

    public static QuizShareDetailsDTO toDTO(QuizShare quizShare){
        QuizShareDetailsDTO quizShareDetailsDTO = new QuizShareDetailsDTO();

        quizShareDetailsDTO.setId(quizShare.getId());
        quizShareDetailsDTO.setSharedAt(quizShare.getShareAt());
        quizShareDetailsDTO.setEmail(quizShare.getUser().getEmail());
        quizShareDetailsDTO.setTitle(quizShare.getQuiz().getTitle());
        quizShareDetailsDTO.setQuizId(quizShare.getQuiz().getId());

        return quizShareDetailsDTO;
    }
}
