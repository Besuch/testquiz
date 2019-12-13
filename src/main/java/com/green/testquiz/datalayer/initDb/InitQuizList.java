package com.green.testquiz.datalayer.initDb;

import com.green.testquiz.datalayer.mocks.MockQuizList;
import com.green.testquiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitQuizList {

    @Autowired
    private QuizRepository quizRepository;

    @PostConstruct
    private void init(){
        if (quizRepository.findAll().isEmpty()){
            MockQuizList.MOCK_QUIZ_LIST.forEach(quiz -> quizRepository.save(quiz));
        }
    }
}
