package com.spaient.assesment.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spaient.assesment.exception.ProcessingException;
import com.spaient.assesment.http.model.HttpResponseEntity;
import com.spaient.assesment.http.model.Status;
import com.spaient.assesment.http.service.HttpProxyInvocationService;
import com.spaient.assesment.model.*;
import com.spaient.assesment.util.Helper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class DaoServiceImpl implements DaoService {

    final HttpProxyInvocationService httpProxyInvocationService;
    final ObjectMapper objectMapper;
    private final Map<String, Object> Cache = new HashMap<>(10);

    @Autowired
    public DaoServiceImpl(HttpProxyInvocationService httpProxyInvocationService, ObjectMapper objectMapper) {
        this.httpProxyInvocationService = httpProxyInvocationService;
        this.objectMapper = objectMapper;
    }

    @Override
    public MovieDetail findPlanetDetails(String type, String name) {
        try {
            if (Cache == null || Cache.isEmpty() || Cache.get(type) == null)
                cronJobSch();
            Planet planet = (Planet) Cache.get(type);
            return getMovieDetail(type, name, planet.getResults());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MovieDetail.builder().build();
    }

    @Override
    public MovieDetail findSpaceshipsDetails(String type, String name) {
        try {
            if (Cache == null || Cache.isEmpty() || Cache.get("people") == null) {
                cronJobSch();
            } else {
                People resp = (People) Cache.get("people");
                return getMovieDetailForType(type, name, resp.getResults());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MovieDetail.builder().build();
    }

    @Override
    public MovieDetail findVehicleDetails(String type, String name) {
        try {
            if (Cache == null || Cache.isEmpty() || Cache.get("people") == null) {
                cronJobSch();
            } else {
                People resp = (People) Cache.get("people");
                return getMovieDetailForType(type, name, resp.getResults());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MovieDetail.builder().build();
    }

    @Override
    public MovieDetail findPeopleDetails(String type, String name) {
        try {
            if (Cache == null || Cache.isEmpty() || Cache.get(type) == null)
                cronJobSch();
            People people = (People) Cache.get(type);
            return getMovieDetail(type, name, people.getResults());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MovieDetail.builder().build();
    }

    @Override
    public MovieDetail findFilmDetails(String type, String name) {
        try {
            if (Cache == null || Cache.isEmpty() || Cache.get("people") == null) {
                cronJobSch();
            } else {
                People resp = (People) Cache.get("people");
                return getMovieDetailForType(type, name, resp.getResults());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return MovieDetail.builder().build();
    }

    @Override
    public MovieDetail findSpeciesDetails(String type, String name) {
        try {
            if (Cache == null || Cache.isEmpty() || Cache.get("people") == null) {
                cronJobSch();
            } else {
                People resp = (People) Cache.get("people");
                return getMovieDetailForType(type, name, resp.getResults());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MovieDetail.builder().build();
    }


    private MovieDetail getMovieDetail(String type, String name, List<JsonNode> results) {
        String searchField = "name";
        if (type.equals("films")) {
            searchField = "title";
        }
        String finalSearchField = searchField;
        List<Object> obj = results.stream().filter(jsonNode -> jsonNode.get(finalSearchField).asText().equals(name)).collect(Collectors.toList());
        return MovieDetail.builder().type(type).name(name).object((obj != null && obj.size() > 0) ? obj.get(0) : null).build();
    }

    private MovieDetail getMovieDetailForType(String type, String name, List<JsonNode> results) throws JsonProcessingException {
        int count = 0;
        for (JsonNode node : results) {
            System.out.println(node.get("name") != null ? node.get("name").asText() : "");
            String nameS = node.get("name") != null ? node.get("name").asText() : "";
            if (nameS.equals(name)) {
                count = objectMapper.readValue(node.get(type).toString(), List.class).size();
                break;
            }
        }
        MovieDetail movieDetail = MovieDetail.builder().name(name)
                .type(type)
                .count(count).build();
        return movieDetail;
    }

    @Override
    public TAPIResponse findListByType(String type) {
        TAPIResponse apiResponse = null;
        Map<String, String> map = new HashMap<>();
        map.put("action", type);
        HttpResponseEntity httpResponseEntity = httpProxyInvocationService.invokeGetCall(map);
        if (httpResponseEntity.getStatus() == Status.SUCCESS) {
            try {
                String response = objectMapper.writeValueAsString(httpResponseEntity.getResponseBody());
                apiResponse = objectMapper.readValue(response, TAPIResponse.class);
            } catch (Exception e) {
                log.error("Failed to map response from client server-{}", httpResponseEntity.getResponseBody());
            }
        } else if (httpResponseEntity.getStatus() == Status.FAILURE) {
            throw new ProcessingException("API Invocation failed.");
            // (TAPIResponse)Cache.get(type);
        }
        return apiResponse;
    }

    @Scheduled(initialDelay = 0, fixedRate = 50000)
    public void cronJobSch() throws Exception {

        for (EventType eventType : EventType.values()) {
            Map<String, String> map = new HashMap<>();
            map.put("action", eventType.getValue());
            HttpResponseEntity httpResponseEntity = httpProxyInvocationService.invokeGetCall(map);
            if (httpResponseEntity.getStatus() == Status.SUCCESS) {
                try {
                    String response = objectMapper.writeValueAsString(httpResponseEntity.getResponseBody());
                    Cache.put(eventType.getValue(), objectMapper.readValue(response, Helper.getClassName(eventType.getValue())));
                } catch (Exception e) {
                    log.error("Failed to map response from client server-{}", httpResponseEntity.getResponseBody());
                }
            } else {
            }
        }

        log.info("TOTAL  {}", Cache);
    }
}
