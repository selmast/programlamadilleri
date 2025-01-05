package com.example.organizationService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Value("${user-group.service}")
    private String userGroupService;

    @Bean
    public RestClient userGroupService() {
        return RestClient.create(userGroupService);
    }
}
