package com.aidost.aigateway.service;
import com.aidost.aigateway.dto.AiRequests.*;
public interface AiGatewayService { String explainDsaProblem(DsaExplainRequest request); String discussDsaApproach(DsaExplainRequest request); String generateProjectPlan(String idea, String techStack, String tier); String prepExistingProject(String projectDetails, String experience, String targetRole, String tier); String generateMockQuestions(GenerateMockQuestionRequest request); String evaluateMockAnswer(EvaluateMockAnswerRequest request); }
