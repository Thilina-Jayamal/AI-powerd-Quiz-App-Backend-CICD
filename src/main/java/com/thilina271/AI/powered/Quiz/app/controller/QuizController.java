package com.thilina271.AI.powered.Quiz.app.controller;

import com.thilina271.AI.powered.Quiz.app.dto.QuizDTO;
import com.thilina271.AI.powered.Quiz.app.model.Quiz;
import com.thilina271.AI.powered.Quiz.app.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/quiz")
public class QuizController {

    private final QuizService quizService;

    @PostMapping
    public Quiz saveQuiz(@RequestBody Quiz quiz){
        return quizService.saveQuiz(quiz);
    }

    @GetMapping("{id}")
    public ResponseEntity<QuizDTO> getQuiz(@PathVariable int id){
        return quizService.getQuiz(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteQuiz(@PathVariable int id){
        return quizService.deleteQuiz(id);
    }

    @GetMapping("all")
    public ResponseEntity<List<Quiz>> getAllQuizess(){
        return quizService.getAllQuizess();
    }

}