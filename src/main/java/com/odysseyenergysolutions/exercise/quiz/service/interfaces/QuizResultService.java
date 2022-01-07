package com.odysseyenergysolutions.exercise.quiz.service.interfaces;

import com.odysseyenergysolutions.exercise.quiz.model.dto.QuizAnswerRequestDto;
import com.odysseyenergysolutions.exercise.quiz.model.dto.QuizResultDto;
import com.odysseyenergysolutions.exercise.quiz.model.entity.QuizResult;

public interface QuizResultService {
    QuizResultDto processQuizResult(QuizAnswerRequestDto quizAnswerRequest);
}
