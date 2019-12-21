package com.green.testquiz.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.*;
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

    private Result resultWithoutStatistic;
    private Result result;
    private String email;
    private ObjectId quizObjectId;
    private String quizId;

    @Before
    public void setUp() {
        objectId = new ObjectId();
        quizId = new ObjectId().toHexString();
        quizObjectId = new ObjectId(quizId);
		email = "Test@gmail.com";

        options = Stream.of(
                Option.builder().optionId(objectId).text("Option1").isCorrect(true).isChecked(true).build(),
                Option.builder().optionId(objectId).text("Option2").isCorrect(false).isChecked(false).build(),
                Option.builder().optionId(objectId).text("Option3").isCorrect(true).isChecked(false).build())
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

        resultWithoutStatistic = Result.builder()
                .resultId(objectId)
                .statistics(null)
                .accountId(objectId)
                .accountEmail(email)
                .cursor(0)
                .quizId(quizObjectId)
                .name("Quiz1")
                .shortDescription("quiz1 short description")
                .longDescription("quiz1 long description")
                .quizMode(QuizMode.ONE_WAY_DIRECTION)
                .questions(questions)
                .build();

        result = Result.builder()
                .resultId(objectId)
                .statistics(50.0)
                .accountId(objectId)
                .accountEmail(email)
                .cursor(1)
                .quizId(quizObjectId)
                .name("Quiz2")
                .shortDescription("quiz2 short description")
                .longDescription("quiz2 long description")
                .quizMode(QuizMode.MULTI_WAY_DIRECTION)
                .questions(questions)
                .build();
    }

    @Test
    public void shouldReturnResultWithoutStatistic() {
		Account account = Account.builder()
                .accountId(objectId)
                .firstName("Name")
                .lastName("Surname")
                .email(email)
                .role(AccountRole.USER)
                .build();
		List<Result> results = Collections.singletonList(resultWithoutStatistic);

		when(accountRepository.findByEmail(email)).thenReturn(account);
		when(resultRepository.findByQuizIdAndAccountId(quizObjectId, objectId)).thenReturn(results);

        Result actual = resultService.getResult(quizId, email);

        Assert.assertEquals(resultWithoutStatistic, actual);
    }

    @Test
    public void getResultTest() {
		Account account = Account.builder()
				.accountId(objectId)
				.firstName("Name")
				.lastName("Surname")
				.email(email)
				.role(AccountRole.USER)
				.build();

		List<Result> results = Collections.singletonList(result);

        Optional<Quiz> optionalQuiz = Optional.ofNullable(Quiz.builder()
				.quizId(quizObjectId)
				.name("Quiz2")
				.shortDescription("quiz2 short description")
				.longDescription("quiz2 long description")
				.quizMode(QuizMode.MULTI_WAY_DIRECTION)
				.questions(questions)
				.build());

        when(accountRepository.findByEmail(email)).thenReturn(account);
        when(resultRepository.findByQuizIdAndAccountId(quizObjectId, objectId)).thenReturn(results);
        when(quizRepository.findById(quizObjectId)).thenReturn(optionalQuiz);


        Result actual = resultService.getResult(quizId, email);

        Result expected = Result.builder()
                .resultId(actual.getResultId())
                .statistics(null)
                .accountId(objectId)
                .accountEmail(email)
                .quizId(quizObjectId)
                .name("Quiz2")
                .shortDescription("quiz2 short description")
                .longDescription("quiz2 long description")
                .quizMode(QuizMode.MULTI_WAY_DIRECTION)
                .questions(questions)
                .build();

        Assert.assertEquals(expected, actual);
	}
}