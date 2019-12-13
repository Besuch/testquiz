package com.green.testquiz.datalayer.entities;

import com.green.testquiz.enums.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.Set;

@Data
@AllArgsConstructor
public class Question {
    @Id
    private ObjectId questionId;
    private String text;
    private String description;
    private QuestionType questionType;
    private Set<Option> options;
}
