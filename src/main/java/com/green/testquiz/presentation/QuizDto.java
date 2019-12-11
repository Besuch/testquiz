package com.green.testquiz.presentation;

import java.util.HashSet;
import java.util.Set;

import com.green.testquiz.enums.QuizMode;

import lombok.Data;

@Data
public class QuizDto {
	String resultId;
	String quizId;
	String name;
	String shortDescription;
	String longDescription;
	QuizMode quizMode;
	Set<QuestionDto> questionDtos;
}