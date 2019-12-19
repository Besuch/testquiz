package com.green.testquiz.presentation;

import com.green.testquiz.enums.QuestionType;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class QuestionDto {
	private String questionId;
	private String text;
	private QuestionType questionType;
	private Set<OptionDto> optionDtos;
}