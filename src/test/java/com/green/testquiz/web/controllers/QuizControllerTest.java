package com.green.testquiz.web.controllers;

import com.green.testquiz.converter.QuizConverter;
import com.green.testquiz.datalayer.entities.Quiz;
import com.green.testquiz.service.QuizService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
    private QuizService quizServiceMock;

    @Mock
    private QuizConverter quizConverterMock;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
    }

    @Test
    void shouldGetQuiz() throws Exception {
        //given
        Quiz quiz = new Quiz();
        String quizId = "1";
        String email = "mail@mail.com";
        String path = BASE_URL + quizId + "?email=" + email;
        when(quizServiceMock.getQuiz(quizId, email)).thenReturn(quiz);
        //when
        mockMvc.perform(
                get(path)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        //then
        verify(quizServiceMock).getQuiz(quizId, email);
        verifyNoMoreInteractions(quizServiceMock);
        verify(quizConverterMock).toDto(quiz);
        verifyNoMoreInteractions(quizConverterMock);
    }
}