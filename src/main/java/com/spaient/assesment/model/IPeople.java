package com.spaient.assesment.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IPeople {
    private String name;
    private String gender;
    private String created;
    private String edited;
    private String planetId;
    private List<String> filmsIds;
    private List<String> speciesIds;
    private List<String> vehiclesIds;
    private List<String> starshipsIds;
}
