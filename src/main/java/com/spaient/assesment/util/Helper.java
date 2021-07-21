package com.spaient.assesment.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spaient.assesment.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Helper {
    static ObjectMapper mapper = new ObjectMapper();

    public static Class getClassName(String type) throws Exception {

        Class result;
        switch (type) {
            case "planets":
                result = Planet.class;
                break;
            case "starships":
                result = Starship.class;
                break;
            case "vehicles":
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

    public static boolean validateStarWarsType(String type) {
        return Arrays.stream(EventType.values()).anyMatch(eventType -> eventType.getValue().equals(type));
    }


    public static PeopleResponse convertDTOinResponse(TAPIResponse tapiResponse) throws IOException {

        PeopleResponse peopleResponse = PeopleResponse.builder()
                .totalCount(tapiResponse.getCount())
                .nextPage(tapiResponse.getNext() != null ? tapiResponse.getNext().substring(tapiResponse.getNext().lastIndexOf('/') - 1) : "")
                .prevPage(tapiResponse.getPrevious() != null ? tapiResponse.getPrevious().substring(tapiResponse.getPrevious().lastIndexOf('/') - 1) : "")
                .peoples(new ArrayList<IPeople>())
                .build();

        for (JsonNode node : tapiResponse.getResults()) {
            IPeople iPeople = new IPeople();
            iPeople.setName(node.get("name") != null ? node.get("name").asText() : "");
            iPeople.setPlanetId(node.get("homeworld") != null ? node.get("homeworld").asText().substring(node.get("homeworld").asText().lastIndexOf("/") - 1) : "");

            if (node.get("films") != null) {
                List<String> films = mapper.readValue(node.get("films").toString(), List.class);
                iPeople.setFilmsIds(films.stream().map(value -> ((String) value).substring(((String) value).lastIndexOf("/") - 1)).collect(Collectors.toList()));
            }
            if (node.get("species") != null) {
                List<String> species = mapper.readValue(node.get("species").toString(), List.class);
                iPeople.setSpeciesIds((List<String>) species.stream().map(value -> ((String) value).substring(((String) value).lastIndexOf("/") - 1)).collect(Collectors.toList()));
            }
            if (node.get("vehicles") != null) {
                List<String> vehicles = mapper.readValue(node.get("vehicles").toString(), List.class);
                iPeople.setVehiclesIds((List<String>) vehicles.stream().map(value -> ((String) value).substring(((String) value).lastIndexOf("/") - 1)).collect(Collectors.toList()));
            }
            if (node.get("starships") != null) {
                List<String> starships = mapper.readValue(node.get("starships").toString(), List.class);
                iPeople.setStarshipsIds((List<String>) starships.stream().map(value -> ((String) value).substring(((String) value).lastIndexOf("/") - 1)).collect(Collectors.toList()));
            }
            peopleResponse.getPeoples().add(iPeople);
        }
        return peopleResponse;
    }
}
