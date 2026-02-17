package com.aidost.aigateway.repository;

import com.aidost.aigateway.model.AiRouteConfig;

import java.util.Optional;

public interface AiRouteConfigRepository {
  Optional<AiRouteConfig> findByOperationAndTier(String operation, String tier);
}
