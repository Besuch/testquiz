package com.green.testquiz.service.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
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
import com.green.testquiz.datalayer.entities.Quiz;
import com.green.testquiz.enums.QuestionType;
import com.green.testquiz.enums.QuizMode;
import com.green.testquiz.repository.QuizRepository;

@ExtendWith(MockitoExtension.class)
public class QuizServiceImplTest {

	@InjectMocks
	private QuizServiceImpl quizService;

	@Mock
	private QuizRepository quizRepository;

	private ObjectId objectId;

	private Set<Option> options;

	private Set<Question> questions;

	private Quiz quiz;

	@BeforeEach
	public void setUp() {
		options = Stream.of(
				Option.builder().optionId(objectId).text("Option1").isCorrect(true).isChecked(true).build(),
				Option.builder().optionId(objectId).text("Option2").isCorrect(true).isChecked(true).build(),
				Option.builder().optionId(objectId).text("Option3").isCorrect(true).isChecked(true).build())
				.collect(Collectors.toSet());

		questions = Stream.of(
				Question.builder()
						.questionId(objectId)
						.text("Question1")
						.questionType(QuestionType.ONE_CHOICE)
						.options(options)
						.build(),

				Question.builder()
						.questionId(objectId)
						.text("Question2")
						.questionType(QuestionType.ONE_CHOICE)
						.options(options)
						.build(),

				Question.builder()
						.questionId(objectId)
						.text("Question3")
						.questionType(QuestionType.ONE_CHOICE)
						.options(options)
						.build())
				.collect(Collectors.toSet());

		quiz = Quiz.builder()
				.quizId(objectId)
				.name("Quiz")
				.shortDescription("short description")
				.longDescription("long description")
				.quizMode(QuizMode.ONE_WAY_DIRECTION)
				.questions(questions)
				.build();
	}

	@Test
	public void getQuiz() {
		String quizId = new ObjectId().toHexString();
		String email = "Test@gmail.com";

		objectId = new ObjectId(quizId);

		when(quizRepository.findById(objectId)).thenReturn(java.util.Optional.ofNullable(quiz));

		Quiz actual = quizService.getQuiz(quizId, email);

		Assert.assertEquals(quiz, actual);
		verify(quizRepository).findById(objectId);
	}

	@Test
	public void findAll() {
		List<Quiz> quizzes = Stream.of(
				quiz,
				Quiz.builder()
						.quizId(objectId)
						.name("Quiz2")
						.shortDescription("short description")
						.longDescription("long description")
						.quizMode(QuizMode.MULTI_WAY_DIRECTION)
						.questions(questions)
						.build())
				.collect(Collectors.toList());

		when(quizRepository.findAll()).thenReturn(quizzes);

		List<Quiz> actual = quizService.findAll();

		Assert.assertEquals(quizzes, actual);
	}

	@Test
	public void create() {
		when(quizRepository.save(quiz)).thenReturn(quiz);

		Quiz actual = quizService.create(quiz);

		Assert.assertEquals(quiz, actual);
		verify(quizRepository).save(quiz);

	}

	@Test
	public void update() {
		when(quizRepository.save(quiz)).thenReturn(quiz);

		Quiz actual = quizService.update(quiz);

		Assert.assertEquals(quiz, actual);
		verify(quizRepository).save(quiz);
	}

	@Test
	public void findById() {
		String quizId = new ObjectId().toHexString();
		objectId = new ObjectId(quizId);

		when(quizRepository.findById(objectId)).thenReturn(java.util.Optional.ofNullable(quiz));

		Quiz actual = quizService.findById(quizId);

		Assert.assertEquals(quiz, actual);
		verify(quizRepository).findById(objectId);
	}

	@Test
	public void delete() {
		String id = new ObjectId().toHexString();

		quizService.delete(id);
	}
}