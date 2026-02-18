package com.aidost.aigateway.provider;

import com.aidost.aigateway.config.AiGatewayProperties;
import com.aidost.aigateway.exception.AiProviderException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OpenAiClient implements AiProviderClient {
  private final AiGatewayProperties properties;
  private final WebClient webClient = WebClient.builder().baseUrl("https://api.openai.com/v1").build();

  @Override
  @SuppressWarnings("unchecked")
  public String complete(String model, String prompt) {
    String key = properties.getKeys().getOpenaiApiKey();
    if (key == null || key.isBlank()) {
      throw new AiProviderException("OPENAI_API_KEY missing. Set aidost.ai.keys.openai-api-key");
    }

    Map<String, Object> body = Map.of(
        "model", model,
        "messages", List.of(Map.of("role", "user", "content", prompt)),
        "temperature", 0.3
    );

    Map<String, Object> response = webClient.post()
        .uri("/chat/completions")
        .header(HttpHeaders.AUTHORIZATION, "Bearer " + key)
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(body)
        .retrieve()
        .bodyToMono(Map.class)
        .block();

    List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
    Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
    return String.valueOf(message.get("content"));
  }
}
