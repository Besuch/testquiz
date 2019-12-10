package com.green.testquiz.web.controllers;

import com.green.testquiz.converter.QuizConverter;
import com.green.testquiz.datalayer.entities.Quiz;
import com.green.testquiz.service.QuizService;
import com.green.testquiz.web.dto.QuizDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuizConverter quizConverter;

    @GetMapping("/api/quiz/{quizId}")
    public QuizDto getQuiz(@PathVariable String quizId, @RequestParam String email) {
        Quiz quiz = quizService.getQuiz(quizId, email);
        return quizConverter.toQuizDto(quiz);
    }

}
