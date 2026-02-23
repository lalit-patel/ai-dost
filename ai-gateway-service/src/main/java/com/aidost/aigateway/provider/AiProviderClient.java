package com.aidost.aigateway.provider;

public interface AiProviderClient {
  String complete(String model, String prompt);
}
