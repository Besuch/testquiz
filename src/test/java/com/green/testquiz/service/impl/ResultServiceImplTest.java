package com.green.testquiz.service.impl;

import com.green.testquiz.datalayer.entities.*;
import com.green.testquiz.enums.AccountRole;
import com.green.testquiz.enums.QuestionType;
import com.green.testquiz.enums.QuizMode;
import com.green.testquiz.exceptions.EntityNotFoundException;
import com.green.testquiz.exceptions.InvalidOperationException;
import com.green.testquiz.exceptions.UnauthorizedException;
import com.green.testquiz.repository.AccountRepository;
import com.green.testquiz.repository.QuizRepository;
import com.green.testquiz.repository.ResultRepository;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

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
    private Account account;
    private String quizId;
    private Option option1;
    private Option option2;
    private Option option3;

    @Before
    public void setUp() {
        objectId = new ObjectId();
        quizId = new ObjectId().toHexString();
        quizObjectId = new ObjectId(quizId);
		email = "Test@gmail.com";

        option1 = Option.builder().optionId(objectId).text("Option1").isCorrect(true).isChecked(false).build();
        option2 = Option.builder().optionId(objectId).text("Option2").isCorrect(false).isChecked(true).build();
        option3 = Option.builder().optionId(objectId).text("Option3").isCorrect(true).isChecked(true).build();

        options = Stream.of(option1, option2, option3).collect(Collectors.toSet());

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

        account = Account.builder()
                .accountId(objectId)
                .firstName("Name")
                .lastName("Surname")
                .email(email)
                .role(AccountRole.USER)
                .build();
    }

    @Test
    public void shouldReturnResultWithoutStatistic() throws UnauthorizedException, EntityNotFoundException {
		Account account = Account.builder()
                .accountId(objectId)
                .firstName("Name")
                .lastName("Surname")
                .email(email)
                .role(AccountRole.USER)
                .build();
		List<Result> results = Collections.singletonList(resultWithoutStatistic);

		when(accountRepository.findByEmail(email)).thenReturn(Optional.ofNullable(account));
		when(resultRepository.findByQuizIdAndAccountId(quizObjectId, objectId)).thenReturn(results);

        Result actual = resultService.startQuiz(quizId, email);

        Assert.assertEquals(resultWithoutStatistic, actual);
    }

    @Test
    public void getResultTest() throws UnauthorizedException, EntityNotFoundException {
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

        when(accountRepository.findByEmail(email)).thenReturn(Optional.ofNullable(account));
        when(resultRepository.findByQuizIdAndAccountId(quizObjectId, objectId)).thenReturn(results);
        when(quizRepository.findById(quizObjectId)).thenReturn(optionalQuiz);


        Result actual = resultService.startQuiz(quizId, email);

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

    @Test
    public void saveTest() throws UnauthorizedException, InvalidOperationException {
        List<String> optionList = Arrays.asList(
                option1.getOptionId().toHexString(),
                option2.getOptionId().toHexString(),
                option3.getOptionId().toHexString());

        List<Result> results = Arrays.asList(result, resultWithoutStatistic);

        when(accountRepository.findByEmail(email)).thenReturn(Optional.ofNullable(account));
        when(resultRepository.findByQuizIdAndAccountId(quizObjectId, account.getAccountId())).thenReturn(results);
        when(resultRepository.save(resultWithoutStatistic)).thenReturn(resultWithoutStatistic);


        Result actualResult = resultService.save(email, quizId, objectId.toHexString(), optionList);

        Assert.assertEquals(resultWithoutStatistic, actualResult);
    }

    @Test
    public void finishTest() throws UnauthorizedException, InvalidOperationException {
        List<String> optionList = Arrays.asList(
                option1.getOptionId().toHexString(),
                option2.getOptionId().toHexString(),
                option3.getOptionId().toHexString());


        List<Result> results = Arrays.asList(result, resultWithoutStatistic);

        when(accountRepository.findByEmail(email)).thenReturn(Optional.ofNullable(account));
        when(resultRepository.findByQuizIdAndAccountId(quizObjectId, account.getAccountId())).thenReturn(results);
        when(resultRepository.save(resultWithoutStatistic)).thenReturn(resultWithoutStatistic);


        Result actualResult = resultService.finishQuiz(email, quizId, objectId.toHexString(), optionList);

        Result expectedResult = Result.builder()
                .resultId(objectId)
                .statistics(0.0)
                .accountId(objectId)
                .accountEmail(email)
                .quizId(quizObjectId)
                .cursor(0)
                .name("Quiz1")
                .shortDescription("quiz1 short description")
                .longDescription("quiz1 long description")
                .quizMode(QuizMode.ONE_WAY_DIRECTION)
                .questions(questions)
                .build();

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findAllTest() {
        Set<Result> expected = Stream.of(result, resultWithoutStatistic).collect(Collectors.toSet());
        List<Result> resultList = new ArrayList<>(expected);

        when(resultRepository.findAll()).thenReturn(resultList);

        Set<Result> actual = resultService.findAll();

        Assert.assertEquals(expected, actual);
	}
}