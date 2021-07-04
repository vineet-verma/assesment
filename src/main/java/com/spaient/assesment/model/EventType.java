package com.spaient.assesment.model;

public enum EventType {

    PLANETS("planets"), SPACESHIPS("starships"), VEHICLE("vehicles"), PEOPLE("people"), FILMS("films"), SPECIES("species");
    private final String type;

    EventType(String type) {
        this.type = type;
    }
    public String getValue(){
        return type;
    }
}
