package com.spaient.assesment.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spaient.assesment.cache.CacheLRU;
import com.spaient.assesment.http.model.HttpResponseEntity;
import com.spaient.assesment.http.model.Status;
import com.spaient.assesment.http.service.HttpProxyInvocationService;
import com.spaient.assesment.model.*;
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
    private final CacheLRU<String, Object> Cache = new CacheLRU<>(2000);

    @Autowired
    public DaoServiceImpl(HttpProxyInvocationService httpProxyInvocationService, ObjectMapper objectMapper) {
        this.httpProxyInvocationService = httpProxyInvocationService;
        this.objectMapper = objectMapper;
    }


    @Scheduled(initialDelay=0, fixedRate=50000)
    public void cronJobSch() throws Exception {

        for (EventType eventType : EventType.values()) {
            Map<String, String> map = new HashMap<>();
            map.put("action", eventType.getValue());
            HttpResponseEntity httpResponseEntity = httpProxyInvocationService.invokeGetCall(map);
            if (httpResponseEntity.getStatus() == Status.SUCCESS) {
                try {
                    String response = objectMapper.writeValueAsString(httpResponseEntity.getResponseBody());
                    Cache.put(eventType.getValue(), objectMapper.readValue(response, getClass(eventType.getValue())));
                } catch (Exception e) {
                    log.error("Failed to map response from client server-{}", httpResponseEntity.getResponseBody());
                    //return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
            }
        }

        log.info("TOTAL  {}", Cache);
    }


    private Class getClass(String type) throws Exception {

        Class result;
        switch (type) {
            case "planets":
                result = Planet.class;
                break;
            case "starships":
                result = Starship.class;
                break;
            case "vehicle":
                result = Vehicle.class;
                break;
            case "people":
                result = People.class;
                break;
            case "films":
                result = Films.class;
                break;
            case "species":
                result = Species.class;
                break;
            default:
                throw new Exception("No matched typed");
        }
        return result;

    }

    @Override
    public MovieDetail findPlanetDetails(String type, String name) {
        try {
           if(Cache==null || Cache.isEmpty())
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
            if(Cache==null || Cache.isEmpty())
                cronJobSch();
            Starship starship = (Starship) Cache.get(type);
            return getMovieDetail(type, name, starship.getResults());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MovieDetail.builder().build();
    }

    @Override
    public MovieDetail findVehicleDetails(String type, String name) {
        try {
            if(Cache==null || Cache.isEmpty())
                cronJobSch();
            Vehicle vehicle = (Vehicle) Cache.get(type);
            return getMovieDetail(type, name, vehicle.getResults());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MovieDetail.builder().build();
    }

    @Override
    public MovieDetail findPeopleDetails(String type, String name) {
        try {
            if(Cache==null || Cache.isEmpty())
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
            if(Cache==null || Cache.isEmpty())
                cronJobSch();
            Films films = (Films) Cache.get(type);
            return getMovieDetail(type, name, films.getResults());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MovieDetail.builder().build();
    }

    @Override
    public MovieDetail findSpeciesDetails(String type, String name) {
        try {
            if(Cache==null || Cache.isEmpty())
                cronJobSch();
            Species species = (Species) Cache.get(type);
            return getMovieDetail(type, name, species.getResults());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MovieDetail.builder().build();
    }

    private MovieDetail getMovieDetail(String type, String name, List<JsonNode> results) {
        String searchField = "name";
        if(type.equals("films")){
            searchField = "title";
        }
        String finalSearchField = searchField;
        List<Object> obj = results.stream().filter(jsonNode -> jsonNode.get(finalSearchField).asText().equals(name)).collect(Collectors.toList());
        return MovieDetail.builder().type(type).name(name).object((obj != null && obj.size() > 0) ? obj.get(0) : null).build();
    }
}
