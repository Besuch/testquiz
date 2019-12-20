package com.green.testquiz.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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

import com.green.testquiz.datalayer.entities.Account;
import com.green.testquiz.datalayer.entities.Option;
import com.green.testquiz.datalayer.entities.Question;
import com.green.testquiz.datalayer.entities.Quiz;
import com.green.testquiz.datalayer.entities.Result;
import com.green.testquiz.enums.AccountRole;
import com.green.testquiz.enums.QuestionType;
import com.green.testquiz.enums.QuizMode;
import com.green.testquiz.repository.AccountRepository;
import com.green.testquiz.repository.QuizRepository;
import com.green.testquiz.repository.ResultRepository;

@RunWith(MockitoJUnitRunner.class)
public class ResultServiceImplTest {

	@InjectMocks
	private ResultServiceImpl resultService;

	@Mock
	private QuizRepository quizRepository;
	@Mock
	private ResultRepository resultRepository;
	@Mock
	private AccountRepository accountRepository;

	private ObjectId objectId;

	private Set<Option> options;
	private Set<Question> questions;

	@Before
	public void setUp() throws Exception {
		objectId = new ObjectId();

		options = Stream.of(
				Option.builder().optionId(objectId).text("Option1").isCorrect(true).isChecked(true).build(),
				Option.builder().optionId(objectId).text("Option2").isCorrect(true).isChecked(true).build(),
				Option.builder().optionId(objectId).text("Option3").isCorrect(true).isChecked(true).build())
				.collect(Collectors.toSet());

		questions = Stream.of(
				Question.builder()
						.questionId(objectId)
						.text("Question1")
						.description("description for Question1")
						.questionType(QuestionType.ONE_CHOICE)
						.options(options)
						.build(),

				Question.builder()
						.questionId(objectId)
						.text("Question2")
						.description("description for Question2")
						.questionType(QuestionType.ONE_CHOICE)
						.options(options)
						.build(),

				Question.builder()
						.questionId(objectId)
						.text("Question3")
						.description("description for Question3")
						.questionType(QuestionType.ONE_CHOICE)
						.options(options)
						.build())
				.collect(Collectors.toSet());
	}

	@Test
	public void getResult() {
		String email = "Test@gmail.com";
		String quizId = new ObjectId().toHexString();
//		String accountId = new ObjectId().toHexString();
//		String resultId = new ObjectId().toHexString();

		ObjectId quizObjectId = new ObjectId(quizId);
//		ObjectId accountObjectId = new ObjectId(accountId);
//		ObjectId resultObjectId = new ObjectId(resultId);

		Result result1 = Result.builder()
				.resultId(objectId)
				.statistics(null)
				.accountId(objectId)
				.accountEmail(email)
				.cursor(0)
				.quizId(quizObjectId)
				.name("Quiz1")
				.shortDescription("quiz short description")
				.longDescription("quiz long description")
				.quizMode(QuizMode.ONE_WAY_DIRECTION)
				.questions(questions)
				.build();

		Result result2 = Result.builder()
				.resultId(objectId)
				.statistics(50.0)
				.accountId(objectId)
				.accountEmail(email)
				.cursor(1)
				.quizId(quizObjectId)
				.name("Quiz2")
				.shortDescription("quiz short description")
				.longDescription("quiz long description")
				.quizMode(QuizMode.MULTI_WAY_DIRECTION)
				.questions(questions)
				.build();

		List<Result> results = Arrays.asList(result1, result2);

		when(resultRepository.findByQuizIdAndAccountId(quizObjectId, objectId)).thenReturn(results);

		Account account = Account.builder()
				.accountId(objectId)
				.firstName("Name")
				.lastName("Surname")
				.email(email)
				.role(AccountRole.USER)
				.build();

		when(accountRepository.findByEmail(email)).thenReturn(account);

		Optional<Quiz> quiz = Optional.ofNullable(Quiz.builder()
				.quizId(quizObjectId)
				.name("Quiz1")
				.shortDescription("quiz short description")
				.longDescription("quiz long description")
				.quizMode(QuizMode.ONE_WAY_DIRECTION)
				.questions(questions)
				.build());

		when(quizRepository.findById(quizObjectId)).thenReturn(quiz);

		Result expected = result1;

		Result actual = resultService.getResult(quizId, email);

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void save() {
	}

	@Test
	public void finishQuiz() {
	}

	@Test
	public void findAll() {
	}
}