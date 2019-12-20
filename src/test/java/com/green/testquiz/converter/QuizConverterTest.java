package com.green.testquiz.converter;

import static org.mockito.Mockito.when;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.green.testquiz.datalayer.entities.Option;
import com.green.testquiz.datalayer.entities.Question;
import com.green.testquiz.datalayer.entities.Quiz;
import com.green.testquiz.datalayer.entities.Result;
import com.green.testquiz.enums.QuestionType;
import com.green.testquiz.enums.QuizMode;
import com.green.testquiz.presentation.OptionDto;
import com.green.testquiz.presentation.QuestionDto;
import com.green.testquiz.presentation.QuizDto;

@RunWith(MockitoJUnitRunner.class)
public class QuizConverterTest {

	@InjectMocks
	private QuizConverter quizConverter;

	@Mock
	private QuestionConverter questionConverter;

	private ObjectId objectId = new ObjectId();

	private Set<Option> options;

	private Set<OptionDto> optionDtos;

	private Question question1;
	private Question question2;
	private Question question3;
	private Set<Question> questions;

	private QuestionDto questionDto1;
	private QuestionDto questionDto2;
	private QuestionDto questionDto3;
	private Set<QuestionDto> questionDtos;

	@Before
	public void init() {
		options = Stream.of(
				Option.builder().optionId(objectId).text("Option1").isCorrect(true).isChecked(true).build(),
				Option.builder().optionId(objectId).text("Option2").isCorrect(false).isChecked(true).build(),
				Option.builder().optionId(objectId).text("Option3").isCorrect(true).isChecked(false).build()
		)
				.collect(Collectors.toSet());

		optionDtos = Stream.of(
				OptionDto.builder().optionId(objectId.toHexString()).text("OptionDto1").isChecked(true).build(),
				OptionDto.builder().optionId(objectId.toHexString()).text("OptionDto2").isChecked(true).build(),
				OptionDto.builder().optionId(objectId.toHexString()).text("OptionDto3").isChecked(false).build()
		)
				.collect(Collectors.toSet());

		question1 = Question.builder()
				.questionId(objectId)
				.text("question1")
				.questionType(QuestionType.ONE_CHOICE)
				.options(options)
				.build();

		question2 = Question.builder()
				.questionId(objectId)
				.text("question2")
				.questionType(QuestionType.ONE_CHOICE)
				.options(options)
				.build();

		question3 = Question.builder()
				.questionId(objectId)
				.text("question3")
				.questionType(QuestionType.ONE_CHOICE)
				.options(options)
				.build();

		questions = Stream.of(question1, question2, question3).collect(Collectors.toSet());

		questionDto1 = QuestionDto.builder()
				.questionId(objectId.toHexString())
				.text("QuestionDto1")
				.questionType(QuestionType.ONE_CHOICE)
				.optionDtos(optionDtos)
				.build();

		questionDto2 = QuestionDto.builder()
				.questionId(objectId.toHexString())
				.text("QuestionDto2")
				.questionType(QuestionType.ONE_CHOICE)
				.optionDtos(optionDtos)
				.build();

		questionDto3 = QuestionDto.builder()
				.questionId(objectId.toHexString())
				.text("QuestionDto3")
				.questionType(QuestionType.MULTIPLE_CHOICE)
				.optionDtos(optionDtos)
				.build();

		questionDtos = Stream.of(questionDto1, questionDto2, questionDto3).collect(Collectors.toSet());
	}

	@Test
	public void toDto() {
		Quiz quiz = Quiz.builder()
				.quizId(objectId)
				.name("Quiz")
				.shortDescription("short description")
				.longDescription("long description")
				.quizMode(QuizMode.ONE_WAY_DIRECTION)
				.questions(questions)
				.build();

		QuizDto expected = QuizDto.builder()
				.quizId(objectId.toHexString())
				.shortDescription("short description")
				.longDescription("long description")
				.name("Quiz")
				.quizMode(QuizMode.ONE_WAY_DIRECTION)
				.questionDtos(questionDtos)
				.build();

		when(questionConverter.toDto(question1)).thenReturn(questionDto1);
		when(questionConverter.toDto(question2)).thenReturn(questionDto2);
		when(questionConverter.toDto(question3)).thenReturn(questionDto3);

		QuizDto actual = quizConverter.toDto(quiz);

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testToDto() {
		Result result = Result.builder()
				.resultId(objectId)
				.statistics(50.0)
				.accountId(objectId)
				.cursor(1)
				.quizId(objectId)
				.name("Quiz")
				.shortDescription("short description")
				.longDescription("long description")
				.quizMode(QuizMode.ONE_WAY_DIRECTION)
				.questions(questions)
				.build();

		QuizDto expected = QuizDto.builder()
				.quizId(objectId.toHexString())
				.shortDescription("short description")
				.longDescription("long description")
				.name("Quiz")
				.quizMode(QuizMode.ONE_WAY_DIRECTION)
				.questionDtos(questionDtos)
				.build();

		when(questionConverter.toDto(question1)).thenReturn(questionDto1);
		when(questionConverter.toDto(question2)).thenReturn(questionDto2);
		when(questionConverter.toDto(question3)).thenReturn(questionDto3);

		QuizDto actual = quizConverter.toDto(result);

		Assert.assertEquals(expected, actual);
	}
}