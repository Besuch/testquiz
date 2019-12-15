package com.green.testquiz.converter;

import com.green.testquiz.datalayer.entities.Result;
import com.green.testquiz.presentation.QuestionDto;
import com.green.testquiz.presentation.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ResultConverter {

    @Autowired
    private QuestionConverter questionConverter;

    public ResultDto toDto(Result result) {
        Set<QuestionDto> questionDtoSet = result.getQuestions().stream()
                .map(questionConverter::toDto)
                .collect(Collectors.toSet());
        return ResultDto.builder()
                .resultId(result.getResultId().toHexString())
                .statistics(result.getStatistics())
                .accountId(result.getAccountId().toHexString())
                .quizId(result.getQuizId().toHexString())
                .name(result.getName())
                .shortDescription(result.getShortDescription())
                .longDescription(result.getLongDescription())
                .quizMode(result.getQuizMode().toString())
                .questionDtoSet(questionDtoSet)
                .build();
    }
}
