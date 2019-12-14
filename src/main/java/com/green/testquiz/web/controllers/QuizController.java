package com.green.testquiz.web.controllers;

import com.green.testquiz.converter.QuizConverter;
import com.green.testquiz.datalayer.entities.Result;
import com.green.testquiz.presentation.QuizDto;
import com.green.testquiz.service.QuizService;
import com.green.testquiz.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        Result result = resultService.getResult(quizId, email);
        return quizConverter.toDto(result);
    }

    @GetMapping("/api/quizzes")
    public List<QuizDto> getQuizzes() {
        return quizService.findAll().stream()
                .map(quizConverter::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/api/progress/quizzes/{quizId}/questions/{questionId}/answers)")
    public ResponseEntity<?> saveAnswer(@PathVariable String quizId, @PathVariable String questionId,
                                        @RequestParam String email, @RequestBody List<String> optionIdList) {
        resultService.save(email, quizId, questionId, optionIdList);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
