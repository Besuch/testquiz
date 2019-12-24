package com.green.testquiz.service;

import com.green.testquiz.datalayer.entities.Result;
import com.green.testquiz.exceptions.InvalidOperationException;
import com.green.testquiz.exceptions.EntityNotFoundException;
import com.green.testquiz.exceptions.UnauthorizedException;

import java.util.List;
import java.util.Set;

public interface ResultService {
    Result startQuiz(String quizId, String email) throws UnauthorizedException, EntityNotFoundException;
    Result save(String email, String quizId, String questionId, List<String> optionIdList) throws UnauthorizedException, InvalidOperationException;
    Result finishQuiz(String email, String quizId, String questionId, List<String> optionIdList) throws UnauthorizedException, InvalidOperationException;
    Set<Result> findAll();
}
