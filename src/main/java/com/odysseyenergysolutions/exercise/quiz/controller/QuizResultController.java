package com.odysseyenergysolutions.exercise.quiz.controller;

import com.odysseyenergysolutions.exercise.quiz.model.dto.QuizAnswerRequestDto;
import com.odysseyenergysolutions.exercise.quiz.model.dto.QuizResultDto;
import com.odysseyenergysolutions.exercise.quiz.service.interfaces.QuizResultService;
import com.odysseyenergysolutions.exercise.quiz.utils.CustomResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDateTime;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/answers")
@Api(tags = "Quiz API Endpoints", description = "The collection of REST API Endpoints to manage quiz on Odyssey Quiz API", hidden = false, produces = "application/json")
public class QuizResultController {

    private final QuizResultService quizResultService;

    @Autowired
    public QuizResultController(QuizResultService quizResultService){
        this.quizResultService = quizResultService;
    }

    @PostMapping(value="/{username}",consumes="application/json",produces="application/json")
    @ApiOperation(value = "Submit Answers", notes = "Endpoint to submit quiz answers.")
    public @ResponseBody
    ResponseEntity<?> processQuizAnswers(@Valid  @RequestBody QuizAnswerRequestDto quizAnswerRequestDto, @PathVariable("username") String username) {

        quizAnswerRequestDto.setUser(username);
        QuizResultDto quizResult = quizResultService.processQuizResult(quizAnswerRequestDto);
        CustomResponse<?> responseBody = new CustomResponse.CustomResponseBuilder<>()
                .withCode("201")
                .withMessage("Answers submitted and result processed successfully.")
                .withTimestamp(LocalDateTime.now())
                .withData(quizResult)
                .withStatus(HttpStatus.CREATED).build();
        return new ResponseEntity<>(responseBody, responseBody.getStatus());

    }
}
