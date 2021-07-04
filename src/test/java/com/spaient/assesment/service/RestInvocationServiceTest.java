package com.spaient.assesment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spaient.assesment.http.model.HttpResponseEntity;
import com.spaient.assesment.http.model.JsonModel;
import com.spaient.assesment.http.service.RestInvocationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@ComponentScan
public class RestInvocationServiceTest {
    @Mock
    RestTemplate restTemplate;

    RestInvocationService restInvocationService;

    @BeforeEach
    void configureApplication() {
        ResponseEntity<Object> entity = new ResponseEntity<>(HttpStatus.ACCEPTED);
        //restTemplate = spy(restTemplate);
        when(restTemplate.exchange(anyString(), any(), any(), (Class<Object>) any())).thenReturn(entity);
        restInvocationService = new RestInvocationService(restTemplate,new ObjectMapper());
    }

    @Test
    public void processHttpRequestTest() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("APIkey", "");
        JsonModel jsonModel = JsonModel.builder().pathQueryParameters(map).build();
        HttpResponseEntity httpResponseEntity = restInvocationService.processHttpRequest("http://stands", HttpMethod.GET, jsonModel);
        Assertions.assertNotNull(httpResponseEntity);
    }
}
