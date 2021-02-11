package com.ezerio.ipinfo.apis.rest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApiConfguration {

    @Bean
    @Qualifier("api.ip.country")
    public RestTemplate apiIpCountry(RestTemplateBuilder builder) {
        return builder
                .rootUri("https://api.ip2country.info")
                .build();
    }

    @Bean
    @Qualifier("api.info.country")
    public RestTemplate apiInfoCountry(RestTemplateBuilder builder) {
        return builder
                .rootUri("https://restcountries.eu/rest/v2/alpha")
                .build();
    }

    @Bean
    @Qualifier("api.foreign.exchange")
    public RestTemplate apiForeignExchange(RestTemplateBuilder builder) {
        return builder
                .rootUri("http://data.fixer.io/api")
                .build();
    }

}
