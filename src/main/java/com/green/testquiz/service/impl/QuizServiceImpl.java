package com.green.testquiz.service.impl;

import com.green.testquiz.datalayer.entities.Option;
import com.green.testquiz.datalayer.entities.Question;
import com.green.testquiz.datalayer.entities.Quiz;
import com.green.testquiz.repository.QuizRepository;
import com.green.testquiz.service.QuizService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Override
    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }

    @Override
    public Quiz create(Quiz quiz) {
        quiz.setQuizId(new ObjectId());
        for(Question question : quiz.getQuestions()) {
            question.setQuizId(new ObjectId());
            for (Option option : question.getOptions()) {
                option.setOptionId(new ObjectId());
            }
        }
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz update(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz findById(String quizId) {
        return quizRepository.findById(new ObjectId(quizId)).orElseThrow(RuntimeException::new);
    }

    @Override
    public void delete(String id) {
        quizRepository.deleteById(new ObjectId(id));
    }
}
