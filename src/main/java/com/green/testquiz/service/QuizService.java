package com.green.testquiz.service;

import com.green.testquiz.datalayer.entities.Quiz;
import java.util.List;

public interface QuizService {
    Quiz getQuiz(String quizId, String email);
    List<Quiz> findAll();
    Quiz create(Quiz quiz);
    Quiz update(Quiz quiz);
    Quiz findById(String quizId);
    void delete(String id);
}
