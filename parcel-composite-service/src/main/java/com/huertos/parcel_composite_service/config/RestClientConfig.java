package com.huertos.parcel_composite_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    RestClient parcelClient(@Value("${parcel.service.url}") String url) {
        return RestClient.builder().baseUrl(url).build();
    }
}