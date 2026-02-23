package com.aidost.aigateway.service.impl;

import com.aidost.aigateway.config.AiGatewayProperties;
import com.aidost.aigateway.dto.AiEnums.AiProvider;
import com.aidost.aigateway.dto.AiRequests.DsaExplainRequest;
import com.aidost.aigateway.dto.AiRequests.EvaluateMockAnswerRequest;
import com.aidost.aigateway.dto.AiRequests.GenerateMockQuestionRequest;
import com.aidost.aigateway.exception.AiProviderException;
import com.aidost.aigateway.provider.AiProviderClient;
import com.aidost.aigateway.provider.AnthropicClient;
import com.aidost.aigateway.provider.LocalModelClient;
import com.aidost.aigateway.provider.OpenAiClient;
import com.aidost.aigateway.service.AiGatewayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductionAiGatewayService implements AiGatewayService {
  private final OpenAiClient openAiClient;
  private final AnthropicClient anthropicClient;
  private final LocalModelClient localModelClient;
  private final AiGatewayProperties properties;

  @Override
  public String explainDsaProblem(DsaExplainRequest request) {
    return invoke("explain-dsa", "Explain this DSA problem clearly with approach and complexity: " + request.getProblemContext());
  }

  @Override
  public String discussDsaApproach(DsaExplainRequest request) {
    return invoke("discuss-dsa", "Review my approach and suggest improvements. Problem: " + request.getProblemContext() + " Approach: " + request.getUserContext());
  }

  @Override
  public String generateProjectPlan(String idea, String techStack, String tier) {
    return invoke("generate-project-plan", "Create project roadmap. Idea: " + idea + ", tech stack: " + techStack + ", tier: " + tier);
  }

  @Override
  public String prepExistingProject(String projectDetails, String experience, String targetRole, String tier) {
    return invoke("prep-existing-project", "Prepare interview narrative. Project: " + projectDetails + " Experience: " + experience + " Target role: " + targetRole + " Tier: " + tier);
  }

  @Override
  public String generateMockQuestions(GenerateMockQuestionRequest request) {
    return invoke("generate-mock-questions", "Generate mock interview questions for stack: " + request.getTechStack() + ", weak areas: " + request.getWeakAreas());
  }

  @Override
  public String evaluateMockAnswer(EvaluateMockAnswerRequest request) {
    return invoke("evaluate-mock-answer", "Evaluate answer. Question: " + request.getQuestion() + " Answer: " + request.getAnswer());
  }

  private String invoke(String operation, String prompt) {
    AiGatewayProperties.Route route = properties.getRoutes().get(operation);
    if (route == null) {
      throw new AiProviderException("No AI route configured for operation: " + operation);
    }
    AiProviderClient client = clientFor(route.getProvider());
    return client.complete(route.getModel(), prompt);
  }

  private AiProviderClient clientFor(AiProvider provider) {
    return switch (provider) {
      case OPENAI -> openAiClient;
      case ANTHROPIC -> anthropicClient;
      case LOCAL -> localModelClient;
    };
  }
}
