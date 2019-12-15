package com.green.testquiz.datalayer.dbinit;

import com.green.testquiz.datalayer.mocks.MockAccountList;
import com.green.testquiz.datalayer.mocks.MockQuizList;
import com.green.testquiz.repository.AccountRepository;
import com.green.testquiz.repository.QuizRepository;
import com.green.testquiz.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbInitializer implements CommandLineRunner {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private ResultRepository resultRepository;

    @Override
    public void run(String... args) throws Exception {
        deleteAll();
        if (accountRepository.findAll().isEmpty()){
            MockAccountList.ACCOUNTS.forEach(account -> accountRepository.save(account));
        }
        if (quizRepository.findAll().isEmpty()){
            MockQuizList.MOCK_QUIZ_LIST.forEach(quiz -> quizRepository.save(quiz));
        }
    }

    private void deleteAll() {
        resultRepository.deleteAll();
        quizRepository.deleteAll();
        accountRepository.deleteAll();
    }
}
