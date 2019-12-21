package com.green.testquiz.presentation;

import com.green.testquiz.enums.QuizMode;
import lombok.Builder;
import lombok.Data;
import java.util.Set;

@Data
@Builder
public class QuizDto {
	String quizId;
	String name;
	String shortDescription;
	String longDescription;
	QuizMode quizMode;
	Set<QuestionDto> questionDtos;
}