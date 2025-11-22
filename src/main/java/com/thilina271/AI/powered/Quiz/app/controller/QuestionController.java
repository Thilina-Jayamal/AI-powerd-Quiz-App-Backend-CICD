package com.thilina271.AI.powered.Quiz.app.controller;

import com.thilina271.AI.powered.Quiz.app.dto.GeneratedQuestionDTO;
import com.thilina271.AI.powered.Quiz.app.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("api/question")
@RestController
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<GeneratedQuestionDTO>> generateQuestions(
            @RequestParam String description,
            @RequestParam int size
    ) {
        return questionService.generateQuestions(description,size);
    }

    @PostMapping("/upload")
    public ResponseEntity<List<GeneratedQuestionDTO>> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("size") int size ) throws IOException {
        return questionService.generateQuestionsFromPDF(file,size);
    }
}
