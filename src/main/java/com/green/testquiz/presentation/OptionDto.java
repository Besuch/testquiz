package com.green.testquiz.presentation;

import lombok.Data;

@Data
public class OptionDto {
	String optionId;
	String text;
	Boolean isChecked;
}