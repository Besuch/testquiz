package com.green.testquiz.service;

import com.green.testquiz.datalayer.entities.Result;

public interface ResultService {
    Result getResult(String quizId, String email);
}
