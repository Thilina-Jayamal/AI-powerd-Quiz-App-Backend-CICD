package com.thilina271.AI.powered.Quiz.app.mapper;

import com.thilina271.AI.powered.Quiz.app.dto.QuizDTO;
import com.thilina271.AI.powered.Quiz.app.model.Quiz;
import lombok.Data;

@Data
public class QuizMapper {
    public static QuizDTO toDTO(Quiz quiz){
        QuizDTO quizDTO = new QuizDTO();

        quizDTO.setQuestions(quiz.getQuestions().stream()
                .map(QuestionMapper::toDTO)
                .toList()
        );
        quizDTO.setTitle(quiz.getTitle());
        quizDTO.setId(quiz.getId());

        return quizDTO;
    }
}
