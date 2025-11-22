package com.thilina271.AI.powered.Quiz.app.controller;

import com.thilina271.AI.powered.Quiz.app.dto.AnswerDTO;
import com.thilina271.AI.powered.Quiz.app.dto.QuizAttemptDTO;
import com.thilina271.AI.powered.Quiz.app.model.QuizAttempt;
import com.thilina271.AI.powered.Quiz.app.service.QuizAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/attempt/quiz")
public class QuizAttemptController {

    private final QuizAttemptService quizAttemptService;

    @PostMapping("{id}")
    public ResponseEntity<QuizAttempt> submitQuiz(@PathVariable int id, @RequestBody List<AnswerDTO> answerDTOS){
        return quizAttemptService.getResult(id,answerDTOS);
    }

    @GetMapping("all")
    public ResponseEntity<List<QuizAttemptDTO>> getAllQuizAttempts(){
        return quizAttemptService.getAttempts();
    }
}
