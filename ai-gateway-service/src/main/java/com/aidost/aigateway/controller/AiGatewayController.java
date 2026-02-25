package com.aidost.aigateway.controller;

import com.aidost.aigateway.dto.AiRequests.DsaExplainRequest;
import com.aidost.aigateway.dto.AiRequests.EvaluateMockAnswerRequest;
import com.aidost.aigateway.dto.AiRequests.GenerateMockQuestionRequest;
import com.aidost.aigateway.service.AiGatewayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai-gateway")
@RequiredArgsConstructor
public class AiGatewayController {
  private final AiGatewayService service;

  @PostMapping("/explain-dsa")
  public String explain(@RequestBody DsaExplainRequest request) {
    return service.explainDsaProblem(request);
  }

  @PostMapping("/discuss-dsa")
  public String discuss(@RequestBody DsaExplainRequest request) {
    return service.discussDsaApproach(request);
  }

  @PostMapping("/generate-mock-questions")
  public String generateMockQuestions(@RequestBody GenerateMockQuestionRequest request) {
    return service.generateMockQuestions(request);
  }

  @PostMapping("/evaluate-mock-answer")
  public String evaluate(@RequestBody EvaluateMockAnswerRequest request) {
    return service.evaluateMockAnswer(request);
  }
}
