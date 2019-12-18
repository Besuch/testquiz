package com.green.testquiz.presentation;

import com.green.testquiz.enums.QuestionType;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class QuestionDto {
	String questionId;
	String text;
	QuestionType questionType;
	Set<OptionDto> optionDtos;
}