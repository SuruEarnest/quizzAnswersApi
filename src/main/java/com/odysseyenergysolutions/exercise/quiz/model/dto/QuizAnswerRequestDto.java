package com.odysseyenergysolutions.exercise.quiz.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuizAnswerRequestDto {

    @NotEmpty(message = "Please specify an answer for question 1")
    private String question1;
    @NotNull(message ="Please specify true or false for question 2")
    private Boolean question2;
    @NotNull(message ="Answer to question 3 is required")
    private Integer question3;

    @JsonIgnore
    private String user;
}
