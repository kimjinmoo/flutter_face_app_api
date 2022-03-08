package com.grepiu.faceapp.extend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * 미들 웨어
 */
@Component
@Slf4j
public class Middleware {

  @Value("${engine.image.api}")
  String engineAPIUrl;

  public void executeEngine(String mixId, String fileUrl) {
    RestTemplate restTemplate = new RestTemplate();

    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

    params.add("uid", mixId);
    params.add("imageUrl", fileUrl);

    ResponseEntity<String> result = restTemplate.postForEntity(engineAPIUrl, params, String.class);
  }
}
