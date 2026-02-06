package com.thejoa703.external;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class ApiChatGpt {

    @Value("${openai.api.key}")
    private String apikey;

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String getAIResponse(String userMessage) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + apikey);

        Map<String, Object> body = new HashMap<>();
        body.put("model", "gpt-4.1-mini");
        body.put("messages", List.of(
                Map.of(
                    "role", "user",
                    "content", userMessage + "일기작성시 일기내용요약해서 이모지로 표현해봐"
                )
        ));
        body.put("max_tokens", 50); // 비용 최소화

        HttpEntity<Map<String, Object>> requestEntity =
                new HttpEntity<>(body, headers);

        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(API_URL, requestEntity, String.class);

        String responseBody = responseEntity.getBody();

        try {
            JsonNode root = objectMapper.readTree(responseBody);
            return root.path("choices")
                       .get(0)
                       .path("message")
                       .path("content")
                       .asText();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("응답 파싱 오류", e);
        }
    }
}

/*
curl https://api.openai.com/v1/chat/completions \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $OPENAI_API_KEY" \
  -d '{
    "model": "gpt-4.1-mini",
    "messages": [
      {
        "role": "user",
        "content": "Hello!"
      }
    ],
    "logprobs": true,
    "top_logprobs": 2
  }'
*/ 
