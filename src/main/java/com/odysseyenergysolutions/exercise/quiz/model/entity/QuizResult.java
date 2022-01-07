package com.odysseyenergysolutions.exercise.quiz.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
public class QuizResult {
  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  private String id;

  private String quizUser;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
  @CreatedDate private LocalDateTime quizDate;

  /**
   * The number of correct answers that the user submitted.
   */
  private Integer correctAnswers;

  /**
   * The number of wrong answers that the user submitted.
   */
  private Integer wrongAnswers;
}
