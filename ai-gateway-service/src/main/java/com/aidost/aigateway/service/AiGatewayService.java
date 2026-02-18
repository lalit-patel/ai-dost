package com.aidost.aigateway.service;

import com.aidost.aigateway.dto.AiRequests.DsaExplainRequest;
import com.aidost.aigateway.dto.AiRequests.EvaluateMockAnswerRequest;
import com.aidost.aigateway.dto.AiRequests.GenerateMockQuestionRequest;

public interface AiGatewayService {
  String explainDsaProblem(DsaExplainRequest request);

  String discussDsaApproach(DsaExplainRequest request);

  String generateProjectPlan(String idea, String techStack, String tier);

  String prepExistingProject(String projectDetails, String experience, String targetRole, String tier);

  String generateMockQuestions(GenerateMockQuestionRequest request);

  String evaluateMockAnswer(EvaluateMockAnswerRequest request);
}
