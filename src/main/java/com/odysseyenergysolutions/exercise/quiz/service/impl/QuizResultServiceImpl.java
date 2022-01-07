package com.odysseyenergysolutions.exercise.quiz.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.odysseyenergysolutions.exercise.quiz.model.dto.CorrectAnswerDto;
import com.odysseyenergysolutions.exercise.quiz.model.dto.QuizAnswerRequestDto;
import com.odysseyenergysolutions.exercise.quiz.model.dto.QuizResultDto;
import com.odysseyenergysolutions.exercise.quiz.model.entity.QuizResult;
import com.odysseyenergysolutions.exercise.quiz.repository.QuizResultRepository;
import com.odysseyenergysolutions.exercise.quiz.service.interfaces.QuizResultService;
import com.odysseyenergysolutions.exercise.quiz.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@Service
public class QuizResultServiceImpl implements QuizResultService {

    private final QuizResultRepository quizResultRepository;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final ModelMapper modelMapper = new ModelMapper();

    private static CorrectAnswerDto correctAnswers;

    @Autowired
    public QuizResultServiceImpl(QuizResultRepository quizResultRepository){
        this.quizResultRepository = quizResultRepository;
    }

    @PostConstruct
    private void loadCorrectAnswers() {

        try {

            String filePath = File.separator.concat("quiz").concat(File.separator).concat("correct_answers.json");
            File file = new File(getClass().getResource(filePath).getFile());
            correctAnswers = objectMapper.readValue(file, CorrectAnswerDto.class);

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public QuizResultDto processQuizResult(QuizAnswerRequestDto quizAnswerRequest) {

        if (quizAnswerRequest.getUser() == null || quizAnswerRequest.getUser().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please specify username");
        }

        QuizResult result =  quizResultRepository.save(analyzeScores(quizAnswerRequest,correctAnswers));
        QuizResultDto quizResultDto = modelMapper.map(result, QuizResultDto.class);
        quizResultDto.setQuizDate(DateUtil.formatDate(result.getQuizDate()));
        return quizResultDto;
    }

    private QuizResult analyzeScores(QuizAnswerRequestDto quizAnswerRequest, CorrectAnswerDto correctAnswers){

        int correctCount = 0, wrongCount = 0;

        if (quizAnswerRequest.getQuestion1().equals(correctAnswers.getQuestion1())) {
            correctCount++;
        } else {
            wrongCount++;
        }

        if (quizAnswerRequest.getQuestion2().equals(correctAnswers.getQuestion2())) {
            correctCount++;
        } else {
            wrongCount++;
        }

        if (quizAnswerRequest.getQuestion3().equals(correctAnswers.getQuestion3())) {
            correctCount++;
        } else {
            wrongCount++;
        }
        QuizResult result = QuizResult.builder()
                .quizDate(LocalDateTime.now())
                .correctAnswers(Integer.valueOf(correctCount))
                .wrongAnswers(Integer.valueOf(wrongCount))
                .quizUser(quizAnswerRequest.getUser())
                .build();
        return result;
    }

}
