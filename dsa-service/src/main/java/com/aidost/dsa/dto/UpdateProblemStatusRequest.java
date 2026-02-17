package com.aidost.dsa.dto;

import com.aidost.dsa.model.ProblemStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateProblemStatusRequest {
  @NotNull
  private ProblemStatus status;
}
