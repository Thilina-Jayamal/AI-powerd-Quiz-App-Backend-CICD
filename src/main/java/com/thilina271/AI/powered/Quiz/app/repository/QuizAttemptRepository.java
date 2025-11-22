package com.thilina271.AI.powered.Quiz.app.repository;

import com.thilina271.AI.powered.Quiz.app.model.QuizAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizAttemptRepository extends JpaRepository<QuizAttempt,Integer> {
}
