package com.spaient.assesment.http;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class HttpRestTemplateBuilder {
    private static final HostnameVerifier PROMISCUOUS_VERIFIER = (s, sslSession) -> true;
    private RestTemplate restTemplate;

    private ClientHttpRequestFactory clientHttpRequestFactory() {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;


        CloseableHttpClient httpClient = HttpClients.custom().build();
        try {
            SSLContextBuilder custom = SSLContexts.custom();
            custom.loadTrustMaterial(null, acceptingTrustStrategy);
            SSLContext sslContext = custom
                    .build();
            SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

            httpClient = HttpClients.custom()
                    .setSSLSocketFactory(csf)
                    .build();
        } catch (Exception e) {

        }


        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create().useSystemProperties().build());
        return factory;
    }

    public RestTemplate buildRestTemplate() {
        if (restTemplate == null) {
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
