package com.thilina271.AI.powered.Quiz.app.repository;

import com.thilina271.AI.powered.Quiz.app.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Integer> {
}
