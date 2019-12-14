package com.green.testquiz.presentation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OptionDto {
	String optionId;
	String text;
	Boolean isChecked;
}