package com.green.testquiz.converter;

import com.green.testquiz.datalayer.entities.Quiz;
import com.green.testquiz.datalayer.entities.Result;
import com.green.testquiz.presentation.QuizDto;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class QuizConverter {
    @Autowired
    QuestionConverter questionConverter;

    public QuizDto toDto(Quiz quiz) {
        return QuizDto.builder()
                .quizId(quiz.getQuizId().toHexString())
                .shortDescription(quiz.getShortDescription())
                .longDescription(quiz.getLongDescription())
                .name(quiz.getName())
                .quizMode(quiz.getQuizMode())
                .questionDtos(quiz.getQuestions().stream()
                        .map(questionConverter::toDto)
                        .collect(Collectors.toSet()))
                .build();
    }

    public QuizDto toDto(Result result) {
        return QuizDto.builder()
                .quizId(result.getQuizId().toHexString())
                .shortDescription(result.getShortDescription())
                .longDescription(result.getLongDescription())
                .name(result.getName())
                .quizMode(result.getQuizMode())
                .questionDtos(result.getQuestions().stream()
                        .map(questionConverter::toDto)
                        .collect(Collectors.toSet()))
                .build();
    }

    public Quiz fromDto(QuizDto quizDto) {
        Quiz quiz = Quiz.builder()
                .quizMode(quizDto.getQuizMode())
                .shortDescription(quizDto.getShortDescription())
                .longDescription(quizDto.getLongDescription())
                .name(quizDto.getName())
                .questions(quizDto.getQuestionDtos().stream()
                    .map(questionConverter::fromDto)
                        .collect(Collectors.toSet()))
                .build();
                if (quizDto.getQuizId() != null) {
                    quiz.setQuizId(new ObjectId(quizDto.getQuizId()));
                }
                return quiz;

    }
}
