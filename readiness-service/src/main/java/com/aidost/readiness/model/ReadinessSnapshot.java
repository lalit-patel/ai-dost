package com.aidost.readiness.model;

import lombok.*;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class ReadinessSnapshot {
  private Long userId;
  private int total;
}
