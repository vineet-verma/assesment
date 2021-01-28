package com.spaient.assesment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spaient.assesment.http.model.HttpResponseEntity;
import com.spaient.assesment.http.model.Status;
import com.spaient.assesment.http.service.HttpProxyInvocationService;
import com.spaient.assesment.model.Standing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class LeagueServiceImpl implements LeagueService {

    final HttpProxyInvocationService httpProxyInvocationService;

    final ObjectMapper objectMapper;

    @Autowired
    public LeagueServiceImpl(HttpProxyInvocationService httpProxyInvocationService, ObjectMapper objectMapper) {
        this.httpProxyInvocationService = httpProxyInvocationService;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Standing> getStandings(String league_id) {
        Map<String, String> map = new HashMap<>();
        map.put("action", "get_standings");
        map.put("league_id", league_id);
        HttpResponseEntity httpResponseEntity = httpProxyInvocationService.invokeGetCall(map);
        if (httpResponseEntity.getStatus() == Status.SUCCESS) {
            try {
                String response = objectMapper.writeValueAsString(httpResponseEntity.getResponseBody());
                List<Standing> standings = objectMapper.readValue(response, new ArrayList<Standing>().getClass());
                return standings;
            } catch (Exception e) {
                log.error("Failed to map response from client server-{}", httpResponseEntity.getResponseBody());
                //return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            //return new ResponseEntity<>(httpResponseEntity.getErrorMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }
}
