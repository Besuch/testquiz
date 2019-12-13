package com.green.testquiz.service;

import com.green.testquiz.datalayer.entities.Quiz;
import java.util.List;

public interface QuizService {
    List<Quiz> findAll();
    Quiz create(Quiz quiz);
    Quiz update(Quiz quiz);
    Quiz findById(String quizId);
    void delete(String id);
}
