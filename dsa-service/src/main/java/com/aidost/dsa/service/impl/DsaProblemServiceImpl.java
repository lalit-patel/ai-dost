package com.aidost.dsa.service.impl;

import com.aidost.dsa.dto.DsaProblemDto;
import com.aidost.dsa.model.Problem;
import com.aidost.dsa.model.ProblemStatus;
import com.aidost.dsa.model.UserProblemState;
import com.aidost.dsa.repository.ProblemRepository;
import com.aidost.dsa.repository.UserProblemStateRepository;
import com.aidost.dsa.service.DsaProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DsaProblemServiceImpl implements DsaProblemService {
  private final ProblemRepository problemRepository;
  private final UserProblemStateRepository userProblemStateRepository;

  @Override
  public List<Problem> getAllProblems() {
    return problemRepository.findAll();
  }

  @Override
  public List<DsaProblemDto> getProblemsForUser(Long userId) {
    List<Problem> problems = problemRepository.findAll();
    Map<Long, UserProblemState> statesByProblem = userProblemStateRepository.findAllByUserId(userId)
        .stream().collect(Collectors.toMap(UserProblemState::getProblemId, Function.identity()));

    return problems.stream().map(problem -> {
      UserProblemState state = statesByProblem.get(problem.getId());
      return DsaProblemDto.builder()
          .id(problem.getId())
          .title(problem.getTitle())
          .link(problem.getLink())
          .topic(problem.getTopic())
          .difficulty(problem.getDifficulty())
          .description(problem.getDescription())
          .status(state != null ? state.getStatus() : ProblemStatus.TODO)
          .notes(state != null ? state.getNotes() : null)
          .build();
    }).toList();
  }

  @Override
  public DsaProblemDto updateStatus(Long userId, Long problemId, ProblemStatus status) {
    Problem problem = problemRepository.findById(problemId).orElseThrow(() -> new IllegalArgumentException("Problem not found"));
    UserProblemState state = userProblemStateRepository.findByUserIdAndProblemId(userId, problemId)
        .orElse(UserProblemState.builder().userId(userId).problemId(problemId).status(ProblemStatus.TODO).build());
    state.setStatus(status);
    if (status == ProblemStatus.DONE) {
      state.setLastSolved(LocalDateTime.now());
    }
    userProblemStateRepository.save(state);
    return toDto(problem, state);
  }

  @Override
  public DsaProblemDto updateNotes(Long userId, Long problemId, String notes) {
    Problem problem = problemRepository.findById(problemId).orElseThrow(() -> new IllegalArgumentException("Problem not found"));
    UserProblemState state = userProblemStateRepository.findByUserIdAndProblemId(userId, problemId)
        .orElse(UserProblemState.builder().userId(userId).problemId(problemId).status(ProblemStatus.TODO).build());
    state.setNotes(notes);
    userProblemStateRepository.save(state);
    return toDto(problem, state);
  }

  private DsaProblemDto toDto(Problem problem, UserProblemState state) {
    return DsaProblemDto.builder()
        .id(problem.getId())
        .title(problem.getTitle())
        .link(problem.getLink())
        .topic(problem.getTopic())
        .difficulty(problem.getDifficulty())
        .description(problem.getDescription())
        .status(state.getStatus())
        .notes(state.getNotes())
        .build();
  }
}
