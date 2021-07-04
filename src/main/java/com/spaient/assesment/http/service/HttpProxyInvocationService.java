package com.spaient.assesment.http.service;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    RestInvocationService restInvocationService;

    @Autowired
    ObjectMapper objectMapper;

    @Value("${API_ENDPOINT}")
    private String apiUrl;


    public HttpResponseEntity invokeGetCall(Map<String, String> paramMappings) {
        JsonModel jsonModel = JsonModel.builder().pathQueryParameters(paramMappings).build();
        return restInvocationService.processHttpRequest(apiUrl, HttpMethod.GET, jsonModel);
    }
}
