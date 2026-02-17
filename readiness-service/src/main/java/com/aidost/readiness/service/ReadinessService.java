package com.aidost.readiness.service;
import com.aidost.readiness.dto.ReadinessResponse;
public interface ReadinessService { ReadinessResponse getScore(Long candidateId); }
