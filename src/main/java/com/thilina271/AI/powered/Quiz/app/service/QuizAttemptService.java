package com.thilina271.AI.powered.Quiz.app.service;

import com.thilina271.AI.powered.Quiz.app.dto.AnswerDTO;
import com.thilina271.AI.powered.Quiz.app.dto.QuizAttemptDTO;
import com.thilina271.AI.powered.Quiz.app.exception.ResourceNotFoundException;
import com.thilina271.AI.powered.Quiz.app.mapper.QuizAttemptMapper;
import com.thilina271.AI.powered.Quiz.app.model.Quiz;
import com.thilina271.AI.powered.Quiz.app.model.QuizAttempt;
import com.thilina271.AI.powered.Quiz.app.model.User;
import com.thilina271.AI.powered.Quiz.app.repository.QuestionRepository;
import com.thilina271.AI.powered.Quiz.app.repository.QuizAttemptRepository;
import com.thilina271.AI.powered.Quiz.app.repository.QuizRepository;
import com.thilina271.AI.powered.Quiz.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequestMapping("api/quiz/attempt")
@RequiredArgsConstructor
public class QuizAttemptService {

    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final QuizAttemptRepository quizAttemptRepository;
    private final QuestionRepository questionRepository;

    public ResponseEntity<QuizAttempt> getResult(int id, List<AnswerDTO> answerDTOS){

        // fetch the quiz from DB
        Quiz quiz = quizRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Quiz not found"));

        int countOfCorrectAnswers = 0;

        for(int i=0;i< answerDTOS.size();i++){
            String correctAnswer = questionRepository.findById(answerDTOS.get(i).getId())
                    .orElseThrow(()->new ResourceNotFoundException("Question Not Found")).getAnswer();

            if(correctAnswer.equals(answerDTOS.get(i).getAnswer())){
                countOfCorrectAnswers++;
            }
        }

        // Fetch current logged-in user;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(()->new ResourceNotFoundException("User not found"));


        QuizAttempt quizAttempt = new QuizAttempt();

        quizAttempt.setQuiz(quiz);
        quizAttempt.setUser(user);
        quizAttempt.setResult((float) countOfCorrectAnswers /(quiz.getQuestions().size()) * 100);

        return new ResponseEntity<>(quizAttemptRepository.save(quizAttempt), HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuizAttemptDTO>> getAttempts(){
        // Fetch current logged-in user;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(()->new ResourceNotFoundException("User not found"));

        List<QuizAttemptDTO> quizAttemptDTOList = user.getAttempts().stream().map(QuizAttemptMapper::toDTO).toList();

        return new ResponseEntity<>(quizAttemptDTOList,HttpStatus.OK);
    }
}
