package com.odysseyenergysolutions.exercise.quiz.exceptions;

import com.odysseyenergysolutions.exercise.quiz.utils.CustomResponse;
import com.odysseyenergysolutions.exercise.quiz.utils.CustomResponse.CustomResponseBuilder;
import com.odysseyenergysolutions.exercise.quiz.utils.ErrorResponseManager;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public final ResponseEntity<CustomResponse<?>> handleResponseStatusExceptions(ResponseStatusException ex, WebRequest request) {

        CustomResponse<?> response = new CustomResponseBuilder<>().withDetail(request.getDescription(false))
                .withCode(String.valueOf(ex.getStatus().value())).withMessage(ex.getReason()).withTimestamp(LocalDateTime.now())
                .withStatus(ex.getStatus()).build();
        return new ResponseEntity<>(response, response.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, List<String>> body = new HashMap<>();

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);

        CustomResponse<?> response = new CustomResponseBuilder<>()
                .withDetail(request.getDescription(false))
                .withCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .withMessage("Invalid request.")
                .withTimestamp(LocalDateTime.now())
                .withStatus(HttpStatus.BAD_REQUEST)
                .withData(body).build();


        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

}
