package com.green.testquiz.datalayer.entities;

import com.green.testquiz.enums.QuizMode;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {
    protected ObjectId quizId;
    protected String name;
    protected String shortDescription;
    protected String longDescription;
    protected QuizMode quizMode;
    protected Set<Question> questions;
}
