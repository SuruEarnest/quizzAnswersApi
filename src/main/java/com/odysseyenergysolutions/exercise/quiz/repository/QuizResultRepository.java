package com.odysseyenergysolutions.exercise.quiz.repository;

import com.odysseyenergysolutions.exercise.quiz.model.entity.QuizResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizResultRepository extends JpaRepository<QuizResult,String> {
}
