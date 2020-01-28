package com.slack.demo.conf;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpConnectionConfig {

    @Bean
    public RestTemplate getCustomRestTemplate() {
        HttpClient httpClient = HttpClients.custom()
                .setMaxConnTotal(10)
                .setMaxConnPerRoute(5)
                .build();

        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        httpRequestFactory.setConnectTimeout(2000);
        httpRequestFactory.setReadTimeout(3000);

        return new RestTemplate(httpRequestFactory);
    }
}
