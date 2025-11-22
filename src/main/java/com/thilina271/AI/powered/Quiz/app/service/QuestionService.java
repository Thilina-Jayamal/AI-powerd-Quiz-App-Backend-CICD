package com.thilina271.AI.powered.Quiz.app.service;

import com.thilina271.AI.powered.Quiz.app.dto.GeneratedQuestionDTO;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class QuestionService {
    private final ChatClient chatClient;

    public QuestionService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public ResponseEntity<List<GeneratedQuestionDTO>> generateQuestions(String topic, int size){
        String userInputTemplate = """
    give me {size} questions about {topic}.
    All question should be correct and should not confuse the student.
    Each questions in following format
        question
        option1
        option2
        option3
        option4
        option5
        answer
    """;

        PromptTemplate promptTemplate = new PromptTemplate(userInputTemplate);
        Prompt prompt = promptTemplate.create(Map.of("size",size,"topic",topic));

        List<GeneratedQuestionDTO> generatedQuestionDTOS = this.chatClient.prompt(prompt)
                .call()
                .entity(new ParameterizedTypeReference<List<GeneratedQuestionDTO>>() {});

        return new ResponseEntity<>(generatedQuestionDTOS, HttpStatus.OK);

    }

    public ResponseEntity<List<GeneratedQuestionDTO>> generateQuestionsFromPDF(MultipartFile file, int size) throws IOException {
        String extractedText = "";

        try (PDDocument document = Loader.loadPDF(file.getBytes())) { //Automatically resource close with try "(document file)" {}
            PDFTextStripper pdfStripper = new PDFTextStripper();
            extractedText = pdfStripper.getText(document);

        }


        String userInputTemplate = """
    give me {size} questions based on the following context.
    {context}
    All question should be correct and should not confuse the student.
    Each questions in following format
        question
        option1
        option2
        option3
        option4
        option5
        answer
    """;

        PromptTemplate promptTemplate = new PromptTemplate(userInputTemplate);
        System.out.println(size);
        Prompt prompt = promptTemplate.create(Map.of("size",size,"context",extractedText));

        List<GeneratedQuestionDTO> generatedQuestionDTOS = this.chatClient.prompt(prompt)
                .call()
                .entity(new ParameterizedTypeReference<List<GeneratedQuestionDTO>>() {});

        return new ResponseEntity<>(generatedQuestionDTOS, HttpStatus.OK);

    }
}
