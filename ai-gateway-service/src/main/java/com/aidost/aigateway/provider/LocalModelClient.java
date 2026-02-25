package com.aidost.aigateway.provider;

import org.springframework.stereotype.Component;

@Component
public class LocalModelClient implements AiProviderClient {
  @Override
  public String complete(String model, String prompt) {
    return "Local model response (" + model + "): " + prompt;
  }
}
