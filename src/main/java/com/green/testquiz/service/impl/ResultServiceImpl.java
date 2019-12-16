package com.green.testquiz.service.impl;

import com.green.testquiz.datalayer.entities.Account;
import com.green.testquiz.datalayer.entities.Option;
import com.green.testquiz.datalayer.entities.Question;
import com.green.testquiz.datalayer.entities.Quiz;
import com.green.testquiz.datalayer.entities.Result;
import com.green.testquiz.datalayer.mocks.MockQuizList;
import com.green.testquiz.enums.QuizMode;
import com.green.testquiz.repository.AccountRepository;
import com.green.testquiz.repository.QuizRepository;
import com.green.testquiz.repository.ResultRepository;
import com.green.testquiz.service.ResultService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Result save(String email, String quizId, String questionId, List<String> optionIdList) {
        Account account = accountRepository.findByEmail(email);
        Result result = resultRepository.findByQuizIdAndAccountId(new ObjectId(quizId), account.getAccountId()).stream()
                .filter(item -> item.getStatistics() == null)
                .findFirst()
                .get();
        Question question = result.getQuestions().stream()
                .filter(item -> questionId.equals(item.getQuestionId().toHexString()))
                .findFirst()
                .get();

        for(Option option : question.getOptions()) {
            if (optionIdList.contains(option.getOptionId().toHexString())) {
                option.setChecked(true);
            } else {
                option.setChecked(false);
            }
        }
        return resultRepository.save(result);
    }

    @Override
    public Result finishQuiz(String email, String quizId, String questionId, List<String> optionIdList) {
        Result result = save(email, quizId, questionId, optionIdList);
        Double score = (double) result.getQuestions().stream()
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
        return MockQuizList.MOCK_RESULT;
    }
}
