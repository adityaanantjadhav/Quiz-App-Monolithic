package com.quizapp.repository;

import com.quizapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuizRepo extends JpaRepository<Quiz,Integer> {
}
