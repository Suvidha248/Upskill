package com.upskiller.backend.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AIService {

    @Value("${ai.api.key}")
    private String apiKey;

    @Value("${ai.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String callLLM(String prompt) {

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(apiKey);

            Map<String, Object> body = Map.of(
                    "model","llama-3.1-70b-versatile",
                    "messages", new Object[]{
                            Map.of("role", "system", "content", "You are Upskiller AI. Provide clear, structured, helpful responses."),
                            Map.of("role", "user", "content", prompt)
                    }
            );

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

            ResponseEntity<Map> response =
                    restTemplate.postForEntity(apiUrl, entity, Map.class);

            Map<String, Object> choice =
                    (Map<String, Object>) ((java.util.List<?>) response.getBody().get("choices")).get(0);

            Map<String, Object> message = (Map<String, Object>) choice.get("message");

            return (String) message.get("content");

        } catch (Exception e) {
            return "AI Error: " + e.getMessage();
        }
    }
}
