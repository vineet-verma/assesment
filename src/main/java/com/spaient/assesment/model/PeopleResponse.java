package com.spaient.assesment.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PeopleResponse {
    private String totalCount;
    private String nextPage;
    private String prevPage;
    List<IPeople> peoples;


}
