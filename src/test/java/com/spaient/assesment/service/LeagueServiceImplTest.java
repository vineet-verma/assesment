package com.spaient.assesment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spaient.assesment.http.service.HttpProxyInvocationService;
import com.spaient.assesment.model.Standing;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@ComponentScan
class LeagueServiceImplTest {

    @Autowired
    LeagueService leagueService;

    @Autowired
    MockMvc mvc;

    @Autowired
    HttpProxyInvocationService httpProxyInvocationService;

    @Autowired
    ObjectMapper objectMapper;

    @Mock
    RestTemplate restTemplate;

    @BeforeEach
    void configureApplication() {
        ResponseEntity<List<Object>> entity = new ResponseEntity<List<Object>>(HttpStatus.ACCEPTED);
        restTemplate = spy(restTemplate);
        when(restTemplate.exchange(anyString(), any(), any(), Matchers.<ParameterizedTypeReference<List<Object>>>any())).thenReturn(entity);
    }
    @Test
    void getStandingsTest() {
        leagueService = new LeagueServiceImpl(httpProxyInvocationService,objectMapper);
        List<Standing> standingList = leagueService.getStandings("148");
        Assertions.assertEquals(20,standingList.size());
    }
}