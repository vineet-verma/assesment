package com.spaient.assesment.http.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spaient.assesment.exception.ProcessingException;
import com.spaient.assesment.http.model.HttpResponseEntity;
import com.spaient.assesment.http.model.JsonModel;
import com.spaient.assesment.http.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Slf4j
@Builder
@Component
@AllArgsConstructor
public class RestInvocationService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public HttpResponseEntity processHttpRequest(String targetURL, HttpMethod httpMethod, JsonModel jsonModel) {
        HttpHeaders httpHeaders = buildHttpBasicAuthRequestHeader();
        HttpEntity<Object> httpEntity = new HttpEntity<>(jsonModel.getBodyParameters(), httpHeaders);

        String mappedURL = mapURLParameter(targetURL, jsonModel.getPathQueryParameters());
        log.info("Triggering EndPoint {} with http entity {}", mappedURL, httpEntity);
        ResponseEntity<Object> response = restTemplate.exchange(mappedURL, httpMethod, httpEntity, Object.class);
        if (response.getStatusCodeValue() >= 200 && response.getStatusCodeValue() <= 210) {
            log.info("Http Invocation Successful");
            Object responseBody = null;
            if (response.getBody() != null)
                responseBody = response.getBody();

            return HttpResponseEntity.builder().status(Status.SUCCESS).responseBody(responseBody).build();
        } else {
            log.error("Http Invocation Failed");
            return HttpResponseEntity.builder().status(Status.FAILURE).errorMsg("[TARGET SERVICE RESPONSE] - " + response.getStatusCodeValue()).build();
        }
    }

    private String mapURLParameter(String url, Map<String, String> pathQueryVariableMappings) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        for (Map.Entry<String, String> entry : pathQueryVariableMappings.entrySet()) {
           // builder.queryParam(entry.getKey(), entry.getValue());
            builder.pathSegment(entry.getValue());
        }
        return builder.toUriString();
    }

    public HttpHeaders buildHttpBasicAuthRequestHeader() {
        HttpHeaders headers = new HttpHeaders();
        //headers.add("Authorization", "Basic ");
        return headers;
    }

}
