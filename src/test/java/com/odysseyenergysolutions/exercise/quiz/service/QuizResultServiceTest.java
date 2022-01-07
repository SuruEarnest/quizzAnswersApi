package com.odysseyenergysolutions.exercise.quiz.service;

import com.odysseyenergysolutions.exercise.quiz.model.dto.QuizAnswerRequestDto;
import com.odysseyenergysolutions.exercise.quiz.model.dto.QuizResultDto;
import com.odysseyenergysolutions.exercise.quiz.repository.QuizResultRepository;
import com.odysseyenergysolutions.exercise.quiz.service.interfaces.QuizResultService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.profiles.active:local")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QuizResultServiceTest {


    @Autowired
    private QuizResultService quizResultService;

    private QuizAnswerRequestDto quizAnswerRequestDto;

    private QuizResultDto quizResultDto;

    @Autowired
    private QuizResultRepository quizResultRepository;



    @BeforeAll
    public void setup() {
        quizAnswerRequestDto = new QuizAnswerRequestDto();
        quizAnswerRequestDto.setUser("Kenny");
        quizAnswerRequestDto.setQuestion1("Mercury");
        quizAnswerRequestDto.setQuestion2(true);
        quizAnswerRequestDto.setQuestion3(10);
    }

    @AfterAll()
    public void tearDown() {
        quizResultRepository.deleteAll();

    }

    @Test
    @Order(1)
    public void testProcessQuizResult() {
        quizResultDto = quizResultService.processQuizResult(quizAnswerRequestDto);
        Assertions.assertTrue(quizResultDto.getCorrectAnswers().equals(3));
    }

    @Test
    @Order(2)
    public void testProcessQuizResultSumOfCorrectAndWrongAnswers() {
        quizResultDto = quizResultService.processQuizResult(quizAnswerRequestDto);
        Assertions.assertTrue((quizResultDto.getCorrectAnswers()+quizResultDto.getWrongAnswers()==3));
    }
}
