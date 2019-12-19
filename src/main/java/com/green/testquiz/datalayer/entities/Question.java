package com.green.testquiz.datalayer.entities;

import com.green.testquiz.enums.QuestionType;
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
public class Question {
    @Id
    private ObjectId questionId;
    private String text;
    private QuestionType questionType;
    private Set<Option> options;
}
