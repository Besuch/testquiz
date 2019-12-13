package com.green.testquiz.service.impl;

import com.green.testquiz.datalayer.entities.*;
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
    private ResultRepository resultRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Result getResult(String quizId, String email) {
        Account account = accountRepository.findByEmail(email);
        Optional<Result> optionalResult = resultRepository.findByQuizIdAndAccountId(new ObjectId(quizId), account.getAccountId()).stream()
                .filter(result -> result.getStatistics() == null)
                .findFirst();

        if (optionalResult.isPresent()) {
            return optionalResult.get();
        } else {
            // TODO QT-16
            return null;
        }
    }
}
