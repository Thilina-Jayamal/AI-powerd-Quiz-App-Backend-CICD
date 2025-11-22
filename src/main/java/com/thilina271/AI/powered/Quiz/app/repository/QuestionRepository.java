package com.thilina271.AI.powered.Quiz.app.repository;

import com.thilina271.AI.powered.Quiz.app.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {
}
