package com.green.testquiz.converter;

import static org.mockito.Mockito.when;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.green.testquiz.datalayer.entities.Option;
import com.green.testquiz.datalayer.entities.Question;
import com.green.testquiz.datalayer.entities.Result;
import com.green.testquiz.enums.QuestionType;
import com.green.testquiz.enums.QuizMode;
import com.green.testquiz.presentation.OptionDto;
import com.green.testquiz.presentation.QuestionDto;
import com.green.testquiz.presentation.ResultDto;

@RunWith(MockitoJUnitRunner.class)
public class ResultConverterTest {

	@InjectMocks
	private ResultConverter resultConverter;

	@Mock
	private QuestionConverter questionConverter;

	@Test
	public void toDto() {
		ObjectId objectId = new ObjectId();

		Set<Option> options = Stream.of(
				Option.builder().optionId(objectId).text("Option1").isCorrect(true).isChecked(true).build(),
				Option.builder().optionId(objectId).text("Option2").isCorrect(false).isChecked(true).build(),
				Option.builder().optionId(objectId).text("Option3").isCorrect(true).isChecked(false).build()
		)
				.collect(Collectors.toSet());

		Set<OptionDto> optionDtos = Stream.of(
				OptionDto.builder().optionId(objectId.toHexString()).text("OptionDto1").isChecked(true).build(),
				OptionDto.builder().optionId(objectId.toHexString()).text("OptionDto2").isChecked(true).build(),
				OptionDto.builder().optionId(objectId.toHexString()).text("OptionDto3").isChecked(false).build()
		)
				.collect(Collectors.toSet());


		Question question1 = Question.builder()
				.questionId(objectId)
				.text("question1")
				.questionType(QuestionType.ONE_CHOICE)
				.options(options)
				.build();

		Question question2 = Question.builder()
				.questionId(objectId)
				.text("question2")
				.questionType(QuestionType.ONE_CHOICE)
				.options(options)
				.build();

		Question question3 = Question.builder()
				.questionId(objectId)
				.text("question3")
				.questionType(QuestionType.MULTIPLE_CHOICE)
				.options(options)
				.build();

		Set<Question> questions = Stream.of(question1, question2, question3).collect(Collectors.toSet());

		QuestionDto questionDto1 = QuestionDto.builder()
				.questionId(objectId.toHexString())
				.text("QuestionDto1")
				.questionType(QuestionType.ONE_CHOICE)
				.optionDtos(optionDtos)
				.build();

		QuestionDto questionDto2 = QuestionDto.builder()
				.questionId(objectId.toHexString())
				.text("QuestionDto2")
				.questionType(QuestionType.ONE_CHOICE)
				.optionDtos(optionDtos)
				.build();

		QuestionDto questionDto3 = QuestionDto.builder()
				.questionId(objectId.toHexString())
				.text("QuestionDto3")
				.questionType(QuestionType.MULTIPLE_CHOICE)
				.optionDtos(optionDtos)
				.build();

		Set<QuestionDto> questionDtos = Stream.of(questionDto1, questionDto2, questionDto3).collect(Collectors.toSet());


		Result result = Result.builder()
				.resultId(objectId)
				.statistics(50.0)
				.accountId(objectId)
				.cursor(1)
				.quizId(objectId)
				.name("Result")
				.shortDescription("short description")
				.longDescription("long description")
				.quizMode(QuizMode.ONE_WAY_DIRECTION)
				.questions(questions)
				.build();

		ResultDto expected = ResultDto.builder()
				.resultId(objectId.toHexString())
				.statistics(50.0)
				.accountId(objectId.toHexString())
				.cursor(null)
				.quizId(objectId.toHexString())
				.name("Result")
				.shortDescription("short description")
				.longDescription("long description")
				.quizMode(QuizMode.ONE_WAY_DIRECTION.toString())
				.questionDtoSet(questionDtos)
				.build();

		when(questionConverter.toDto(question1)).thenReturn(questionDto1);
		when(questionConverter.toDto(question2)).thenReturn(questionDto2);
		when(questionConverter.toDto(question3)).thenReturn(questionDto3);

		ResultDto actual = resultConverter.toDto(result);

		Assert.assertEquals(expected, actual);

	}
}