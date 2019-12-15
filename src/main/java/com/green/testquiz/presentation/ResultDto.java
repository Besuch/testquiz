package com.green.testquiz.presentation;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class ResultDto {
    private String resultId;
    private Double statistics;
    private String accountId;
    private Integer cursor;
    private String quizId;
    private String name;
    private String shortDescription;
    private String longDescription;
    private String quizMode;
    private Set<QuestionDto> questionDtoSet;
}
