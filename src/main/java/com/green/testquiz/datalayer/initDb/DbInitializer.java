package com.green.testquiz.datalayer.initDb;

import com.green.testquiz.datalayer.mocks.MockAccountList;
import com.green.testquiz.datalayer.mocks.MockQuizList;
import com.green.testquiz.repository.AccountRepository;
import com.green.testquiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DbInitializer implements CommandLineRunner {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Override
    public void run(String... args) throws Exception {
        if (accountRepository.findAll().isEmpty()){
            MockAccountList.ACCOUNTS.forEach(account -> accountRepository.save(account));
        }
        if (quizRepository.findAll().isEmpty()){
            MockQuizList.MOCK_QUIZ_LIST.forEach(quiz -> quizRepository.save(quiz));
        }
    }
}
