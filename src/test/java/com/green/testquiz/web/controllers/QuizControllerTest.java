package com.green.testquiz.web.controllers;

import com.green.testquiz.converter.QuizConverter;
import com.green.testquiz.datalayer.entities.Result;
import com.green.testquiz.enums.QuizMode;
import com.green.testquiz.presentation.QuizDto;
import com.green.testquiz.service.QuizService;
import com.green.testquiz.service.ResultService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class QuizControllerTest {

    private final static String BASE_URL = "/api/quiz/";
    private MockMvc mockMvc;

    @InjectMocks
    private QuizController controller;

    @Mock
    private ResultService resultServiceMock;

    @Mock
    private QuizConverter quizConverterMock;

    @Mock
    private QuizService quizService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
    }

    @Test
    void shouldGetQuiz() throws Exception {
        //given
        String quizId = "507f1f77bcf86cd799439011";
        String email = "mail@mail.com";
        Result result = new Result(new ObjectId(), 15d, new ObjectId(),
                email,3, new ObjectId(quizId), "quizName", "shortDescription",
                "longDescription", QuizMode.ONE_WAY_DIRECTION, new HashSet<>());

        String path = BASE_URL + quizId + "?email=" + email;
        when(resultServiceMock.startQuiz(quizId, email)).thenReturn(result);
        //when
        mockMvc.perform(
                get(path)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        //then
        verify(resultServiceMock).startQuiz(quizId, email);
        verifyNoMoreInteractions(resultServiceMock);
        verify(quizConverterMock).toDto(result);
        verifyNoMoreInteractions(quizConverterMock);
    }
}