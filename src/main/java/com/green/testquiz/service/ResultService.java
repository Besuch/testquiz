package com.green.testquiz.service;

import com.green.testquiz.datalayer.entities.Result;
import com.green.testquiz.exceptions.BadRequestException;
import com.green.testquiz.exceptions.NotFoundException;
import com.green.testquiz.exceptions.UnauthorizedException;

import java.util.List;
import java.util.Set;

public interface ResultService {
    Result startQuiz(String quizId, String email) throws UnauthorizedException, NotFoundException;
    Result save(String email, String quizId, String questionId, List<String> optionIdList) throws UnauthorizedException, BadRequestException;
    Result finishQuiz(String email, String quizId, String questionId, List<String> optionIdList) throws UnauthorizedException, BadRequestException;
    Set<Result> findAll();
}
