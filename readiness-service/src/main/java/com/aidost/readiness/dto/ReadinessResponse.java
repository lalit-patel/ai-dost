package com.aidost.readiness.dto;
import lombok.*;
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor public class ReadinessResponse { private Long userId; private int dsa; private int coreTech; private int projects; private int communication; private int total; }
