package com.green.testquiz.service;

import com.green.testquiz.datalayer.entities.Quiz;
import com.green.testquiz.repo.QuizRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizService {

    @Autowired
    private QuizRepo quizRepo;

    public Quiz getQuiz(String quizId, String email) {
        return quizRepo.findById(quizId);
    }
}
