package com.green.testquiz.web.controllers;

import com.green.testquiz.converter.QuizConverter;
import com.green.testquiz.datalayer.entities.Quiz;
import com.green.testquiz.presentation.QuizDto;
import com.green.testquiz.service.QuizService;

import com.green.testquiz.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class QuizController {

    @Autowired
    private QuizConverter quizConverter;

    @Autowired
    private QuizService quizService;

    @Autowired
    private ResultService resultService;

    @GetMapping("/api/quiz/{quizId}")
    public QuizDto getQuiz(@PathVariable String quizId, @RequestParam String email) {
        Quiz quiz = quizService.getQuiz(quizId, email);
        return quizConverter.toDto(quiz);
        //TODO QT-16
    }

    @GetMapping("/api/quizzes")
    public List<QuizDto> getQuizzes() {
        return quizService.findAll().stream()
                .map(quizConverter::toDto)
                .collect(Collectors.toList());
    }
}
