package com.thilina271.AI.powered.Quiz.app.service;

import com.thilina271.AI.powered.Quiz.app.dto.QuizDTO;
import com.thilina271.AI.powered.Quiz.app.exception.BadRequestException;
import com.thilina271.AI.powered.Quiz.app.exception.ResourceNotFoundException;
import com.thilina271.AI.powered.Quiz.app.mapper.QuizMapper;
import com.thilina271.AI.powered.Quiz.app.model.Quiz;
import com.thilina271.AI.powered.Quiz.app.model.User;
import com.thilina271.AI.powered.Quiz.app.repository.QuizAttemptRepository;
import com.thilina271.AI.powered.Quiz.app.repository.QuizRepository;
import com.thilina271.AI.powered.Quiz.app.repository.QuizShareRepository;
import com.thilina271.AI.powered.Quiz.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final QuizAttemptRepository quizAttemptRepository;

    private final QuizShareRepository quizShareRepository;

    public Quiz saveQuiz(Quiz quiz) {

        // Fetch current logged-in user;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName())
                        .orElseThrow(()->new ResourceNotFoundException("User not found"));

        quiz.setUser(user);

        // link each question to the quiz before saving
        if (quiz.getQuestions() != null) {
            quiz.getQuestions().forEach(q -> q.setQuiz(quiz));
        }

        return quizRepository.save(quiz);
    }

    public ResponseEntity<QuizDTO> getQuiz(int id){
        // Fetch current logged-in user;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(()->new ResourceNotFoundException("User not found"));

        // fetch quiz
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Quiz not found"));

        // Check if logged-in user in shared list
        boolean isSharedUser = quiz.getQuizShares().stream()
                .anyMatch(quizShare -> quizShare.getUser().getEmail().equals(user.getEmail()));

        // check whether user has access permission or not
        if(!user.getEmail().equals(quiz.getUser().getEmail())){
            if(!isSharedUser){
                throw new BadRequestException("You're not allowed to access");
            }
        }

        return new ResponseEntity<>(QuizMapper.toDTO(quiz),HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> deleteQuiz(int id){
        // Fetch current logged-in user;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(()->new ResourceNotFoundException("User not found"));

        // fetch quiz
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Quiz not found"));

        if(!quiz.getUser().getEmail().equals(user.getEmail())){
            throw new BadRequestException("You're not allowed to delete");
        }

        quizRepository.deleteById(id);
        return new ResponseEntity<>("Deleted successfully Quiz id "+id,HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<List<Quiz>> getAllQuizess(){
        // Fetch current logged-in user;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(()->new ResourceNotFoundException("User not found"));

        // fetch quiz
        List<Quiz> quizess = user.getQuizzes();

        return new ResponseEntity<>(quizess,HttpStatus.OK);
    }

}
