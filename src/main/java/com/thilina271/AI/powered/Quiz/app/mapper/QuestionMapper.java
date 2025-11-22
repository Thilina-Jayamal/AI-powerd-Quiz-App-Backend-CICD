package com.thilina271.AI.powered.Quiz.app.mapper;

import com.thilina271.AI.powered.Quiz.app.dto.QuestionDTO;
import com.thilina271.AI.powered.Quiz.app.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionMapper {
    public static QuestionDTO toDTO(Question question){
        QuestionDTO questionDTO = new QuestionDTO();

        questionDTO.setId(question.getId());
        questionDTO.setQuestion(question.getQuestion());

        List<String> options = new ArrayList<>();
        options.add(question.getOption1());
        options.add(question.getOption2());
        options.add(question.getOption3());
        options.add(question.getOption4());
        options.add(question.getOption5());

        questionDTO.setOptions(options);

        return questionDTO;
    }
}
