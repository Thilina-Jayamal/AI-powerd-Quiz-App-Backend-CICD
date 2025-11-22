package com.thilina271.AI.powered.Quiz.app.repository;

import com.thilina271.AI.powered.Quiz.app.model.Quiz;
import com.thilina271.AI.powered.Quiz.app.model.QuizShare;
import com.thilina271.AI.powered.Quiz.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizShareRepository extends JpaRepository<QuizShare,Integer> {

    @Query("""
    SELECT COUNT(q) > 0
    FROM QuizShare q
    WHERE q.user = :user AND q.quiz = :quiz
    """)
    boolean existsByQuizIdAndUserId(Quiz quiz, User user);

    @Query("""
    SELECT q
    FROM QuizShare q
    WHERE q.user = :user
    """)
    List<QuizShare> findByUser(User user);

    @Modifying
    @Query("""
        DELETE FROM QuizShare q
        WHERE q.user.email = :email AND q.quiz.id = :quizId
        """)
    int deleteByQuizIdAndUserId(int quizId, String email);
}
