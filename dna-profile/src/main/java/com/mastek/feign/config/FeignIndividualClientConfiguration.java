package com.mastek.feign.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignIndividualClientConfiguration {

    @Value("${api.username}")
    private String apiUsername;

    @Value("${api.password}")
    private String apiPassword;

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor(apiUsername, apiPassword);
    }

}
