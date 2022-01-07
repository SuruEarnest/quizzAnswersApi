package com.odysseyenergysolutions.exercise.quiz.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;

@Component
public class ErrorResponseManager {
    public static String getErrorMessages(BindingResult result) {
        return result.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.joining(","));
    }
}