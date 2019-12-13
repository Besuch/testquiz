package com.green.testquiz.service.impl;

import com.green.testquiz.datalayer.entities.*;
import com.green.testquiz.enums.QuizMode;
import com.green.testquiz.repository.AccountRepository;
import com.green.testquiz.repository.QuizRepository;
import com.green.testquiz.repository.ResultRepository;
import com.green.testquiz.service.QuizService;
import com.green.testquiz.service.ResultService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Result getResult(String quizId, String email) {
        Result result;
        //TODO if not found logic
        Account account = accountRepository.findByEmail(email);
        Optional<Result> optionalResult = resultRepository.findByQuizIdAndAccountId(new ObjectId(quizId), account.getAccountId()).stream()
                .filter(item -> item.getStatistics() == null)
                .findFirst();

        if (optionalResult.isPresent()) {
            result = optionalResult.get();
        } else {
            Quiz quiz = quizRepository.findById(new ObjectId(quizId)).get();
            result = new Result(
                    new ObjectId(),
                    null,
                    account.getAccountId(),
                    quiz.getQuizMode() == QuizMode.ONE_WAY_DIRECTION ? 0 : null,
                    quiz.getQuizId(),
                    quiz.getName(),
                    quiz.getShortDescription(),
                    quiz.getLongDescription(),
                    quiz.getQuizMode(),
                    quiz.getQuestions()
                    );
            resultRepository.save(result);
        }
        return result;
    }
}
