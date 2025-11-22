package com.thilina271.AI.powered.Quiz.app.controller;

import com.thilina271.AI.powered.Quiz.app.dto.QuizDTO;
import com.thilina271.AI.powered.Quiz.app.dto.QuizShareDetailsDTO;
import com.thilina271.AI.powered.Quiz.app.model.QuizShare;
import com.thilina271.AI.powered.Quiz.app.service.QuizShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/quiz/share")
public class QuizShareController {

    private final QuizShareService quizShareService;

    @PostMapping("{id}")
    public ResponseEntity<String> shareQuiz(@PathVariable int id, @Param("email") String email){
        return quizShareService.shareQuiz(id,email);
    }

    @DeleteMapping("delete")
    public ResponseEntity<Void> deleteQuizShare(@Param("email") String email, @Param("id") int id){
        return quizShareService.deleteQuizShare(email, id);
    }

    @GetMapping("{id}")
    public ResponseEntity<List<QuizShareDetailsDTO>> getQuizShares(@PathVariable int id){
        return quizShareService.getQuizShares(id);
    }

    @GetMapping("all")
    public List<QuizDTO> getQuizSharedWithMe(){
        return quizShareService.getQuizSharedWithMe();
    }
}
