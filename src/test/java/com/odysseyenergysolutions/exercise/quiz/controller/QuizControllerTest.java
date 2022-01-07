package com.odysseyenergysolutions.exercise.quiz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.odysseyenergysolutions.exercise.quiz.model.dto.QuizAnswerRequestDto;
import com.odysseyenergysolutions.exercise.quiz.model.dto.QuizResultDto;
import com.odysseyenergysolutions.exercise.quiz.service.interfaces.QuizResultService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.when;

@SpringBootTest(properties = "spring.profiles.active:local")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class QuizControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private QuizResultService quizResultService;

    private MockMvc mvc;

    private QuizAnswerRequestDto quizAnswerRequestDto;

    private QuizResultDto quizResultDto;

    @BeforeAll
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        quizAnswerRequestDto = new QuizAnswerRequestDto();
        quizAnswerRequestDto.setQuestion1("Mars");
        quizAnswerRequestDto.setQuestion2(Boolean.TRUE);
        quizAnswerRequestDto.setQuestion3(Integer.valueOf(10));
        quizAnswerRequestDto.setUser("King");
    }

    @Test
    @DisplayName("Can Process Answers")
    public void testProcessAnswers() throws Exception {
        when(quizResultService.processQuizResult(quizAnswerRequestDto)).thenReturn(quizResultDto);
        mvc.perform(post("/answers/king")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(quizResultDto))
                        .characterEncoding("utf-8"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()));
    }
}
