package com.green.testquiz.converter;

import com.green.testquiz.datalayer.entities.Quiz;

import com.green.testquiz.presentation.QuizDto;
import org.springframework.stereotype.Component;

@Component
public class QuizConverter {
    public QuizDto toQuizDto(Quiz quiz) {
        return new QuizDto();
    }
    public Quiz fromQuizDto(QuizDto quizDto) {
        return new Quiz();
    }
}
