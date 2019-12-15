package com.green.testquiz.datalayer.entities;

import com.green.testquiz.enums.QuizMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.Set;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@Builder
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
