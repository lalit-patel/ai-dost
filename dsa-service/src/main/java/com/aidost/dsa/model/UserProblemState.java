package com.aidost.dsa.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_problem_states", uniqueConstraints = @UniqueConstraint(columnNames = {"userId", "problemId"}))
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class UserProblemState {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private Long userId;
  @Column(nullable = false)
  private Long problemId;
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ProblemStatus status;
  @Column(length = 3000)
  private String notes;
  private LocalDateTime lastSolved;
}
