package com.aidost.dsa.service;

import com.aidost.dsa.dto.DsaProblemDto;
import com.aidost.dsa.model.Problem;
import com.aidost.dsa.model.ProblemStatus;

import java.util.List;

public interface DsaProblemService {
  List<Problem> getAllProblems();
  List<DsaProblemDto> getProblemsForUser(Long userId);
  DsaProblemDto updateStatus(Long userId, Long problemId, ProblemStatus status);
  DsaProblemDto updateNotes(Long userId, Long problemId, String notes);
}
