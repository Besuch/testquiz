package com.green.testquiz.repo;

import com.green.testquiz.datalayer.entities.Quiz;

import org.springframework.stereotype.Repository;

@Repository
public class QuizRepo {
    public Quiz findById(String quizId) {
        return new Quiz();
    }
}
