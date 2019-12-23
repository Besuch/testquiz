package com.green.testquiz.service.impl;

import com.green.testquiz.datalayer.entities.*;
import com.green.testquiz.enums.QuizMode;
import com.green.testquiz.exceptions.BadRequestException;
import com.green.testquiz.exceptions.NotFoundException;
import com.green.testquiz.exceptions.UnauthorizedException;
import com.green.testquiz.repository.AccountRepository;
import com.green.testquiz.repository.QuizRepository;
import com.green.testquiz.repository.ResultRepository;
import com.green.testquiz.service.ResultService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Result startQuiz(String quizId, String email) throws UnauthorizedException, NotFoundException {
        Account account = accountRepository.findByEmail(email).orElseThrow(UnauthorizedException::new);
        Optional<Result> optionalResult = resultRepository.findByQuizIdAndAccountId(new ObjectId(quizId), account.getAccountId()).stream()
                .filter(item -> item.getStatistics() == null)
                .findFirst();
        if (optionalResult.isPresent()) {
            return optionalResult.get();
        }
        Quiz quiz = quizRepository.findById(new ObjectId(quizId)).orElseThrow(NotFoundException::new);
        Integer cursor = quiz.getQuizMode() == QuizMode.ONE_WAY_DIRECTION ? 0 : null;
        Result result = Result.builder()
                .resultId(new ObjectId())
                .accountId(account.getAccountId())
                .accountEmail(account.getEmail())
                .cursor(cursor)
                .quizId(quiz.getQuizId())
                .name(quiz.getName())
                .shortDescription(quiz.getShortDescription())
                .longDescription(quiz.getLongDescription())
                .quizMode(quiz.getQuizMode())
                .questions(quiz.getQuestions())
                .build();
        resultRepository.save(result);
        return result;
    }

    @Override
    public Result save(String email, String quizId, String questionId, List<String> optionIdList)
            throws UnauthorizedException, BadRequestException {
        Account account = accountRepository.findByEmail(email).orElseThrow(UnauthorizedException::new);
        Result result = resultRepository.findByQuizIdAndAccountId(new ObjectId(quizId), account.getAccountId()).stream()
                .filter(item -> item.getStatistics() == null)
                .findFirst()
                .orElseThrow(BadRequestException::new);
        Question question = result.getQuestions().stream()
                .filter(item -> questionId.equals(item.getQuestionId().toHexString()))
                .findFirst()
                .orElseThrow(BadRequestException::new);
        question.getOptions()
                .forEach(option -> option.setChecked(optionIdList.contains(option.getOptionId().toHexString())));
        return resultRepository.save(result);
    }

    @Override
    public Result finishQuiz(String email, String quizId, String questionId, List<String> optionIdList)
            throws UnauthorizedException, BadRequestException {
        Result result = save(email, quizId, questionId, optionIdList);
        double score = (double) result.getQuestions().stream()
                .filter(question -> {
                    for(Option option : question.getOptions()) {
                        if (option.isChecked() != option.isCorrect()) {
                            return false;
                        }
                    }
                    return true;
                })
                .count();
        Double percents = score / result.getQuestions().size() * 100;
        result.setStatistics(percents);
        return resultRepository.save(result);
    }

    @Override
    public Set<Result> findAll() {
        return new HashSet<>(resultRepository.findAll());
    }
}
