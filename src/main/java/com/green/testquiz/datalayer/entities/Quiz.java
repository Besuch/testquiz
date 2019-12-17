package com.green.testquiz.datalayer.entities;

import com.green.testquiz.enums.QuizMode;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Quiz {
    @Id
    private ObjectId quizId;
    private String name;
    private String shortDescription;
    private String longDescription;
    private QuizMode quizMode;
    private Set<Question> questions = new HashSet<>();

}
