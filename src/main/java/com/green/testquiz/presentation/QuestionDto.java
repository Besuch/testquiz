package com.green.testquiz.presentation;

import java.util.HashSet;
import java.util.Set;

import com.green.testquiz.enums.QuestionType;

import lombok.Data;

@Data
public class QuestionDto {
	String questionId;
	String text;
	QuestionType questionType;
	Set<OptionDto> optionDtos;
}