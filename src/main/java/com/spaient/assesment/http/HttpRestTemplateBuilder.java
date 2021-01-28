package com.spaient.assesment.http;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class HttpRestTemplateBuilder {

    private RestTemplate restTemplate;

    private ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create().useSystemProperties().build());
        return factory;
    }

    public RestTemplate buildRestTemplate() {
        if(restTemplate == null) {
            RestTemplate template = new RestTemplate(clientHttpRequestFactory());
            List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();

            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

            converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
            messageConverters.add(converter);
            template.setMessageConverters(messageConverters);
            restTemplate = template;
        }

        return restTemplate;
    }
}
