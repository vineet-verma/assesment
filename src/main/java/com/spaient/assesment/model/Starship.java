package com.spaient.assesment.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Starship {
    private int count;
    private String next;
    private List<JsonNode> results;
}
