package com.aidost.aigateway.model;

import lombok.*;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class AiRouteConfig {
  private String operation;
  private String tier;
  private String provider;
  private String model;
}
