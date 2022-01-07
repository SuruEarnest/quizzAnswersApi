package com.odysseyenergysolutions.exercise.quiz.model.dto;
import lombok.Data;

@Data
public class QuizResultDto {

    private String id;

    private String quizUser;

    private String quizDate;

    private Integer correctAnswers;

    private Integer wrongAnswers;
}
