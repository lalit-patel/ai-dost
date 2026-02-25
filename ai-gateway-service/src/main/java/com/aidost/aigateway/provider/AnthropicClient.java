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
public class AnthropicClient implements AiProviderClient {
  private final AiGatewayProperties properties;
  private final WebClient webClient = WebClient.builder().baseUrl("https://api.anthropic.com/v1").build();

  @Override
  @SuppressWarnings("unchecked")
  public String complete(String model, String prompt) {
    String key = properties.getKeys().getAnthropicApiKey();
    if (key == null || key.isBlank()) {
      throw new AiProviderException("ANTHROPIC_API_KEY missing. Set aidost.ai.keys.anthropic-api-key");
    }

    Map<String, Object> body = Map.of(
        "model", model,
        "max_tokens", 700,
        "messages", List.of(Map.of("role", "user", "content", prompt))
    );

    Map<String, Object> response = webClient.post()
        .uri("/messages")
        .header("x-api-key", key)
        .header("anthropic-version", "2023-06-01")
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .bodyValue(body)
        .retrieve()
        .bodyToMono(Map.class)
        .block();

    List<Map<String, Object>> content = (List<Map<String, Object>>) response.get("content");
    return String.valueOf(content.get(0).get("text"));
  }
}
