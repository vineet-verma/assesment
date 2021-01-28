package com.spaient.assesment.http.model;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class JsonModel {

    Map<String, String> pathQueryParameters = new HashMap<String, String>();

    Object bodyParameters = new HashMap<String, String>();

}
