package com.aidost.dsa.dto;

import com.aidost.dsa.model.ProblemStatus;
import lombok.*;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class DsaProblemDto {
  private Long id;
  private String title;
  private String link;
  private String topic;
  private String difficulty;
  private String description;
  private ProblemStatus status;
  private String notes;
}
