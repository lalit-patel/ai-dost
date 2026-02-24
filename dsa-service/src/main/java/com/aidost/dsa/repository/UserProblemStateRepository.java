package com.aidost.dsa.repository;

import com.aidost.dsa.model.UserProblemState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserProblemStateRepository extends JpaRepository<UserProblemState, Long> {
  Optional<UserProblemState> findByUserIdAndProblemId(Long userId, Long problemId);
  List<UserProblemState> findAllByUserId(Long userId);
}
