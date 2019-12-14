package com.green.testquiz.converter;

import com.green.testquiz.datalayer.entities.Question;
import com.green.testquiz.presentation.QuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class QuestionConverter {
    @Autowired
    OptionConverter optionConverter;

    public QuestionDto toDto(Question question) {
        return QuestionDto.builder()
                .questionId(question.getQuestionId().toHexString())
                .questionType(question.getQuestionType())
                .optionDtos(question.getOptions().stream()
                        .map(optionConverter::toDto)
                        .collect(Collectors.toSet()))
                .text(question.getText())
                .build();
    }
}
