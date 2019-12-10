package com.green.testquiz.datalayer.entities;

import com.green.testquiz.enums.QuizMode;

import java.util.HashSet;
import java.util.Set;

import org.bson.types.ObjectId;

import lombok.Data;

@Data
public class Question {
    private ObjectId quizId;
    private String name;
    private String shortDescription;
    private String longDescription;
    private QuizMode quizMode;
    private Set<Question> questions = new HashSet<>();
}
