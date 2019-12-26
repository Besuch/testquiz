package com.green.testquiz.converter;

import static org.mockito.Mockito.when;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.green.testquiz.datalayer.entities.Option;
import com.green.testquiz.datalayer.entities.Question;
import com.green.testquiz.enums.QuestionType;
import com.green.testquiz.presentation.OptionDto;
import com.green.testquiz.presentation.QuestionDto;

@ExtendWith(MockitoExtension.class)
public class QuestionConverterTest {

	@InjectMocks
	private QuestionConverter questionConverter = new QuestionConverter();

	@Mock
	private OptionConverter optionConverter;

	private ObjectId objectId;

	private Option option1;
	private Option option2;
	private Option option3;
	private Set<Option> options;

	private OptionDto optionDto1;
	private OptionDto optionDto2;
	private OptionDto optionDto3;
	private Set<OptionDto> optionDtos;

	@BeforeEach
	public void setUp() {
		objectId = new ObjectId();

		option1 = new Option(objectId, "Option1", true, false);
		option2 = new Option(objectId, "Option2", true, false);
		option3 = new Option(objectId, "Option3", false, true);
		options = Stream.of(option1, option2, option3).collect(Collectors.toSet());

		optionDto1 = OptionDto.builder()
				.optionId(objectId.toHexString())
				.text("Option1")
				.isChecked(false)
				.build();

		optionDto2 = OptionDto.builder()
				.optionId(objectId.toHexString())
				.text("Option2")
				.isChecked(false)
				.build();

		optionDto3 = OptionDto.builder()
				.optionId(objectId.toHexString())
				.text("Option3")
				.isChecked(true)
				.build();
		optionDtos = Stream.of(optionDto1, optionDto2, optionDto3).collect(Collectors.toSet());
	}

	@Test
	public void toDto() {
		Question question = Question.builder()
				.questionId(objectId)
				.text("Question")
				.questionType(QuestionType.ONE_CHOICE)
				.options(options)
				.build();

		QuestionDto expected = QuestionDto.builder()
				.questionId(objectId.toHexString())
				.questionType(QuestionType.ONE_CHOICE)
				.text("Question")
				.optionDtos(optionDtos)
				.build();

		when(optionConverter.toDto(option1)).thenReturn(optionDto1);
		when(optionConverter.toDto(option2)).thenReturn(optionDto2);
		when(optionConverter.toDto(option3)).thenReturn(optionDto3);

		QuestionDto actual = questionConverter.toDto(question);

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void fromDto() {
		QuestionDto questionDto = QuestionDto.builder()
				.questionId(objectId.toHexString())
				.questionType(QuestionType.ONE_CHOICE)
				.text("Question")
				.optionDtos(optionDtos)
				.build();

		Question expected = Question.builder()
				.questionId(objectId)
				.text("Question")
				.questionType(QuestionType.ONE_CHOICE)
				.options(options)
				.build();

		when(optionConverter.fromDto(optionDto1)).thenReturn(option1);
		when(optionConverter.fromDto(optionDto2)).thenReturn(option2);
		when(optionConverter.fromDto(optionDto3)).thenReturn(option3);

		Question actual = questionConverter.fromDto(questionDto);

		Assert.assertEquals(expected, actual);
	}
}