package com.green.testquiz.datalayer.entities;

import com.green.testquiz.enums.QuizMode;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Set;

@Data
@AllArgsConstructor
public class Result {
    @Id
    private ObjectId resultId;
    private Double statistics;
    private ObjectId accountId;
    private Integer cursor;
    private ObjectId quizId;
    private String name;
    private String shortDescription;
    private String longDescription;
    private QuizMode quizMode;
    private Set<Question> questions;
}
