package com.aidost.dsa.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "problems")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class Problem {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String title;
  private String link;
  private String topic;
  private String difficulty;
  @Column(length = 3000)
  private String description;
}
