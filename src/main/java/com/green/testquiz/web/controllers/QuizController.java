package com.green.testquiz.web.controllers;

import com.green.testquiz.converter.QuizConverter;
import com.green.testquiz.converter.ResultConverter;
import com.green.testquiz.datalayer.entities.Quiz;
import com.green.testquiz.datalayer.entities.Result;
import com.green.testquiz.exceptions.InvalidOperationException;
import com.green.testquiz.exceptions.EntityNotFoundException;
import com.green.testquiz.exceptions.UnauthorizedException;
import com.green.testquiz.presentation.QuizDto;
import com.green.testquiz.presentation.ResultDto;
import com.green.testquiz.service.QuizService;
import com.green.testquiz.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class QuizController {

    @Autowired
    private QuizConverter quizConverter;

    @Autowired
    private QuizService quizService;

    @Autowired
    private ResultService resultService;

    @Autowired
    private ResultConverter resultConverter;

    @GetMapping("/api/quiz/{quizId}")
    public QuizDto getQuiz(@PathVariable String quizId, @RequestParam String email)
            throws UnauthorizedException, EntityNotFoundException {
        Result result = resultService.startQuiz(quizId, email);
        return quizConverter.toDto(result);
    }

    @GetMapping("/api/quizzes")
    public List<QuizDto> getQuizzes() {
        return quizService.findAll().stream()
                .map(quizConverter::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/api/progress/quizzes/{quizId}/questions/{questionId}/answers")
    public ResponseEntity saveAnswer(@PathVariable String quizId, @PathVariable String questionId,
                                        @RequestParam String email, @RequestBody List<String> optionIdList)
            throws UnauthorizedException, InvalidOperationException {
        resultService.save(email, quizId, questionId, optionIdList);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/api/result/{quizId}/questions/{questionId}/answers")
    public ResponseEntity<Double> finishQuiz(@PathVariable String quizId, @PathVariable String questionId,
                                        @RequestParam String email, @RequestBody List<String> optionIdList)
            throws UnauthorizedException, InvalidOperationException {
        Result result = resultService.finishQuiz(email, quizId, questionId, optionIdList);
        return new ResponseEntity<>(result.getStatistics(), HttpStatus.OK);
    }

    @GetMapping("/api/results")
    public Set<ResultDto> findAllResults() {
        return resultService.findAll().stream()
                .map(resultConverter::toDto)
                .collect(Collectors.toSet());
    }

    @PostMapping("/api/quizes")
    public QuizDto createQuiz(@RequestBody QuizDto quizDto) {
        Quiz quiz = quizConverter.fromDto(quizDto);
        quiz = quizService.create(quiz);
        return quizConverter.toDto(quiz);
    }
}
