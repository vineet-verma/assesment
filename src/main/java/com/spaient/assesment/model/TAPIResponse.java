package com.spaient.assesment.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class TAPIResponse {
    private String count;
    private String next;
    private String previous;
    private List<JsonNode> results;
}
