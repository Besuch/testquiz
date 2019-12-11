package com.green.testquiz.datalayer.entities;

import com.green.testquiz.enums.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Set;

@Data
@AllArgsConstructor
public class Question {
    private ObjectId quizId;
    private String name;
    private String shortDescription;
    private String longDescription;
    private QuestionType quizMode;
    private Set<Option> options;
}
