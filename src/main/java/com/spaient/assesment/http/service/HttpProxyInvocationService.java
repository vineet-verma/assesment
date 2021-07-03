package com.spaient.assesment.http.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spaient.assesment.http.HttpRestTemplateBuilder;
import com.spaient.assesment.http.model.HttpResponseEntity;
import com.spaient.assesment.http.model.JsonModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class HttpProxyInvocationService {

    @Autowired
    HttpRestTemplateBuilder httpRestTemplateBuilder;

    @Autowired
    RestInvocationService restInvocationService;

    @Autowired
    ObjectMapper objectMapper;

    @Value("${API_ENDPOINT}")
    private String apiUrl;

    @Value("${API_KEY}")
    private String apiKey;


    public HttpResponseEntity invokeGetCall(Map<String, String> paramMappings) {
        paramMappings.put("APIkey", apiKey);
        JsonModel jsonModel = JsonModel.builder().pathQueryParameters(paramMappings).build();
        /*RestInvocationService restInvocationService = RestInvocationService.builder()
                .objectMapper(objectMapper).build();*/

        /*RestInvocationService restInvocationService = RestInvocationService.builder()
                .restTemplate(httpRestTemplateBuilder.buildRestTemplate()).objectMapper(objectMapper).build();*/
        return restInvocationService.processHttpRequest(apiUrl, HttpMethod.GET, jsonModel);
    }

    /*public HttpResponseEntity invokePutCall(Map<String, String> paramMappings, Object requestBody) {
        JsonModel jsonModel = JsonModel.builder().pathQueryParameters(paramMappings).bodyParameters(requestBody).build();
        RestInvocationService restInvocationService = RestInvocationService.builder().restTemplate(httpRestTemplateBuilder.buildRestTemplate()).objectMapper(objectMapper).build();
        return restInvocationService.processHttpRequest(apiUrl, HttpMethod.PUT, jsonModel);
    }*/

}
