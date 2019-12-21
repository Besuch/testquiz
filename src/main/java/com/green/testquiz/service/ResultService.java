package com.green.testquiz.service;

import com.green.testquiz.datalayer.entities.Result;

import java.util.List;
import java.util.Set;

public interface ResultService {
    Result getResult(String quizId, String email);
    Result save(String email, String quizId, String questionId, List<String> optionIdList);
    Result finishQuiz(String email, String quizId, String questionId, List<String> optionIdList);
    Set<Result> findAll();
}
