package com.aidost.aigateway.dto;
import lombok.*;import com.aidost.aigateway.dto.AiEnums.AiTier;
public class AiRequests { @Getter @Setter public static class DsaExplainRequest { private String problemContext; private String userContext; private AiTier tier; } @Getter @Setter public static class GenerateMockQuestionRequest { private String techStack; private String weakAreas; private AiTier tier; } @Getter @Setter public static class EvaluateMockAnswerRequest { private String question; private String answer; private AiTier tier; }}
