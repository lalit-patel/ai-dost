package com.aidost.readiness.repository;

import com.aidost.readiness.model.ReadinessSnapshot;

import java.util.Optional;

public interface ReadinessRepository {
  Optional<ReadinessSnapshot> findByUserId(Long userId);
}
