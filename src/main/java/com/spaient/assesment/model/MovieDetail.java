package com.spaient.assesment.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MovieDetail {
    private String type;
    private String count;
    private String name;
    private Object object;
}
