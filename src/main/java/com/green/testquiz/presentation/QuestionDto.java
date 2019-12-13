package com.green.testquiz.presentation;

import java.util.HashSet;
import java.util.Set;

import com.green.testquiz.enums.QuestionType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionDto {
	String questionId;
	String text;
	QuestionType questionType;
	Set<OptionDto> optionDtos;
}