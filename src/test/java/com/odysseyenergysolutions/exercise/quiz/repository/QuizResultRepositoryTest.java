package com.odysseyenergysolutions.exercise.quiz.repository;

import com.odysseyenergysolutions.exercise.quiz.model.entity.QuizResult;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class QuizResultRepositoryTest {

    private QuizResult savedQuizResult;
    private QuizResult quizResult;

    @Autowired
    private QuizResultRepository quizResultRepository;

    @BeforeAll
    public void setUp() {
        quizResult = QuizResult.builder()
                .quizDate(LocalDateTime.now())
                .wrongAnswers(Integer.valueOf(2))
                .correctAnswers(Integer.valueOf(1))
                .quizUser("Kenneth").build();

    }

    @AfterAll
    public void tearDown() {
        quizResultRepository.deleteAll();
    }

    @Test
    public void testSaveQuizResultRepository(){
        savedQuizResult = quizResultRepository.save(quizResult);
        assertTrue(!savedQuizResult.getId().isEmpty());
    }
}
