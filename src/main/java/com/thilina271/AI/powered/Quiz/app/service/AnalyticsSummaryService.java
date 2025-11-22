package com.thilina271.AI.powered.Quiz.app.service;

import com.thilina271.AI.powered.Quiz.app.dto.AnalyticsSummaryDTO;
import com.thilina271.AI.powered.Quiz.app.exception.ResourceNotFoundException;
import com.thilina271.AI.powered.Quiz.app.model.QuizAttempt;
import com.thilina271.AI.powered.Quiz.app.model.User;
import com.thilina271.AI.powered.Quiz.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyticsSummaryService {
    private final UserRepository userRepository;

    public ResponseEntity<AnalyticsSummaryDTO> getSummary(){
        // Fetch current logged-in user;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(()->new ResourceNotFoundException("User not found"));

        int totalAttempts = user.getAttempts().size();
        int totalQuizess = user.getQuizzes().size();

        List<Integer> totalSharesWithQuiz = user.getQuizzes().stream().map(quiz->quiz.getQuizShares().size()).toList();
        int totalShares = 0;
        for(int x: totalSharesWithQuiz){
            totalShares+=x;
        }

        List<Float> scoreList = user.getAttempts().stream().map(QuizAttempt::getResult).toList();
        float avarageScore = 0;
        float totalScore = 0;
        for (float x : scoreList){
            totalScore+=x;
        }
        avarageScore = totalScore/scoreList.size();


        AnalyticsSummaryDTO analyticsSummaryDTO = new AnalyticsSummaryDTO();
        analyticsSummaryDTO.setAverageScore(avarageScore);
        analyticsSummaryDTO.setQuizzesAttempted(totalAttempts);
        analyticsSummaryDTO.setSharedQuizzes(totalShares);
        analyticsSummaryDTO.setQuizzesCreated(totalQuizess);

        return new ResponseEntity<>(analyticsSummaryDTO, HttpStatus.OK);
    }

}
