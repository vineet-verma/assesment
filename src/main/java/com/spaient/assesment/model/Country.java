package com.spaient.assesment.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Country {
    private String country_id;
    private String country_name;
    private String country_logo;
}
