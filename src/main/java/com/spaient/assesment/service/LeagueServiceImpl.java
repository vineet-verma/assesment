package com.spaient.assesment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spaient.assesment.cache.CacheLRU;
import com.spaient.assesment.http.model.HttpResponseEntity;
import com.spaient.assesment.http.model.Status;
import com.spaient.assesment.http.service.HttpProxyInvocationService;
import com.spaient.assesment.model.Standing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.net.ssl.HostnameVerifier;
import java.util.*;

@Slf4j
@Component
public class LeagueServiceImpl implements LeagueService {

    final HttpProxyInvocationService httpProxyInvocationService;
    final ObjectMapper objectMapper;
    private final CacheLRU<String, List<Standing>> cache = new CacheLRU<>(2000);

    @Autowired
    public LeagueServiceImpl(HttpProxyInvocationService httpProxyInvocationService, ObjectMapper objectMapper) {
        this.httpProxyInvocationService = httpProxyInvocationService;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Standing> getStandings(String league_id) {
        if (cache.get(league_id) != null) {
            return cache.get(league_id);
        } else {
            Map<String, String> map = new HashMap<>();
            map.put("action", "get_standings");
            map.put("league_id", league_id);
            HttpResponseEntity httpResponseEntity = httpProxyInvocationService.invokeGetCall(map);
            List<Standing> standings = Collections.emptyList();
            if (httpResponseEntity.getStatus() == Status.SUCCESS) {
                try {
                    String response = objectMapper.writeValueAsString(httpResponseEntity.getResponseBody());
                    standings = objectMapper.readValue(response, new ArrayList<Standing>().getClass());
                    cache.put(league_id, standings);
                } catch (Exception e) {
                    log.error("Failed to map response from client server-{}", httpResponseEntity.getResponseBody());
                    //return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                //return new ResponseEntity<>(httpResponseEntity.getErrorMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return standings;
        }
    }

    //Below method fetches the standings and store in cache for now, Ideally we should store this feed in our database and should use caching on top of that
    /*@Scheduled(cron = "0 * 9 * * ?")
    public void cronJobSch() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("action", "get_standings");
       // map.put("league_id", league_id);
        HttpResponseEntity httpResponseEntity = httpProxyInvocationService.invokeGetCall(map);
        if (httpResponseEntity.getStatus() == Status.SUCCESS) {
            try {
                String response = objectMapper.writeValueAsString(httpResponseEntity.getResponseBody());
                List<Standing> standings = objectMapper.readValue(response, new ArrayList<Standing>().getClass());
                cache.put("", standings);
            } catch (Exception e) {
                log.error("Failed to map response from client server-{}", httpResponseEntity.getResponseBody());
                //return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
             }
    }*/
}
