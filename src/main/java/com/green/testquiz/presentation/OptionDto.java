package com.green.testquiz.presentation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OptionDto {
	private String optionId;
	private String text;
	private boolean isChecked;
	private boolean isCorrect;
}