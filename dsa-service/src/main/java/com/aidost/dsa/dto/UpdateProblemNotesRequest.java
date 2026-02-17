package com.aidost.dsa.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateProblemNotesRequest {
  @NotBlank
  private String notes;
}
