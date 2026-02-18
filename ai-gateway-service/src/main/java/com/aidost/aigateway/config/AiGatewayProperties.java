package com.aidost.aigateway.config;

import com.aidost.aigateway.dto.AiEnums.AiProvider;
import com.aidost.aigateway.dto.AiEnums.AiTier;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = "aidost.ai")
public class AiGatewayProperties {
  private ProviderKeys keys = new ProviderKeys();
  private Map<String, Route> routes = new HashMap<>();

  @Getter
  @Setter
  public static class ProviderKeys {
    private String openaiApiKey;
    private String anthropicApiKey;
  }

  @Getter
  @Setter
  public static class Route {
    private AiProvider provider;
    private String model;
    private AiTier tier;
  }
}
