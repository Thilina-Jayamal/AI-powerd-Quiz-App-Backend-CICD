package com.thilina271.AI.powered.Quiz.app.service;

import com.thilina271.AI.powered.Quiz.app.dto.QuizDTO;
import com.thilina271.AI.powered.Quiz.app.dto.QuizShareDetailsDTO;
import com.thilina271.AI.powered.Quiz.app.exception.BadRequestException;
import com.thilina271.AI.powered.Quiz.app.exception.ResourceNotFoundException;
import com.thilina271.AI.powered.Quiz.app.mapper.QuizMapper;
import com.thilina271.AI.powered.Quiz.app.mapper.QuizShareMapper;
import com.thilina271.AI.powered.Quiz.app.model.Quiz;
import com.thilina271.AI.powered.Quiz.app.model.QuizShare;
import com.thilina271.AI.powered.Quiz.app.model.User;
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

@Service
@RequiredArgsConstructor
public class QuizShareService {

    private final QuizShareRepository quizShareRepository;
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;

    public ResponseEntity<String> shareQuiz(int id, String email){
        // fetch quiz
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Quiz not found"));

        // Fetch current logged-in user;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(()->new ResourceNotFoundException("User not found"));

        if(email.equals(user.getEmail())){
            throw new BadRequestException("You are trying to share your quiz with yourself");
        }

        if(!user.getEmail().equals(quiz.getUser().getEmail())){
            throw new BadRequestException("You're not allowed to share");
        }

        // fetch shared user
        User sharedUser = userRepository.findByEmail(email)
                .orElseThrow(()->new ResourceNotFoundException("User not found with this "+email+" email id"));

        boolean alreadyShared = quizShareRepository.existsByQuizIdAndUserId(quiz, sharedUser);
        if (alreadyShared) {
            throw new BadRequestException("You have been already shared this quiz with that user");
        }

        QuizShare quizShare = new QuizShare();

        quizShare.setUser(sharedUser);
        quizShare.setQuiz(quiz);

        quizShareRepository.save(quizShare);

        return new ResponseEntity<>("You have shared your quiz with this "+email+" email", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Void> deleteQuizShare(String email, int id){
        // Fetch current logged-in user;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(()->new ResourceNotFoundException("User not found"));

        // fetch quiz
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Quiz not found"));

        // prevent unauthorized delete
        if(!quiz.getUser().getEmail().equals(user.getEmail())){
            throw new BadRequestException("You are not allowed to delete");
        }

        int out = quizShareRepository.deleteByQuizIdAndUserId(quiz.getId(), email);
        System.out.println(out);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    public ResponseEntity<List<QuizShareDetailsDTO>> getQuizShares(int id){
        // Fetch current logged-in user;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(()->new ResourceNotFoundException("User not found"));

        // fetch quiz
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Quiz not found"));

        if(!quiz.getUser().getEmail().equals(user.getEmail())){
            throw new BadRequestException("You are not allowed to access");
        }

        List<QuizShareDetailsDTO> quizShareDetailsDTOS =  quiz.getQuizShares().stream().map(QuizShareMapper::toDTO).toList();
        return new ResponseEntity<>(quizShareDetailsDTOS,HttpStatus.OK);
    }

    public List<QuizDTO> getQuizSharedWithMe(){
        // Fetch current logged-in user;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(()->new ResourceNotFoundException("User not found"));

        List<QuizShare> quizShares =  quizShareRepository.findByUser(user);
        List<QuizDTO> quizDTOList = quizShares.stream().map((quizShare)->{
            return QuizMapper.toDTO(quizShare.getQuiz());
        }).toList();

        return quizDTOList;
    }

}
