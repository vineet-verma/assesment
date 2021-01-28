package com.spaient.assesment.http.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class HttpResponseEntity {

    private Status status;

    private String errorMsg;

    private Object responseBody;

}
