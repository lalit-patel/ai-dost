package com.aidost.aigateway.service.impl;

import com.aidost.aigateway.dto.AiRequests.DsaExplainRequest;
import com.aidost.aigateway.dto.AiRequests.EvaluateMockAnswerRequest;
import com.aidost.aigateway.dto.AiRequests.GenerateMockQuestionRequest;
import com.aidost.aigateway.service.AiGatewayService;
import org.springframework.stereotype.Service;

@Service
public class StubAiGatewayService implements AiGatewayService {
  @Override
  public String explainDsaProblem(DsaExplainRequest request) {
    return "[stub] DSA explanation for: " + request.getProblemContext();
  }

  @Override
  public String discussDsaApproach(DsaExplainRequest request) {
    return "[stub] Approach discussion: " + request.getUserContext();
  }

  @Override
  public String generateProjectPlan(String idea, String techStack, String tier) {
    return "[stub] Plan for " + idea + " using " + techStack + " tier=" + tier;
  }

  @Override
  public String prepExistingProject(String projectDetails, String experience, String targetRole, String tier) {
    return "[stub] Prepare project for " + targetRole;
  }

  @Override
  public String generateMockQuestions(GenerateMockQuestionRequest request) {
    return "[stub] Mock questions for " + request.getTechStack() + " weak=" + request.getWeakAreas();
  }

  @Override
  public String evaluateMockAnswer(EvaluateMockAnswerRequest request) {
    return "[stub] Evaluation score: 7/10";
  }
}
