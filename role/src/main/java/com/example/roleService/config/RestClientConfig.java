package com.example.roleService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {
    @Value("${permission.service}")
    private String permissionService;

    @Bean
    public RestClient permissionService() {
        return RestClient.create(permissionService);
    }
}
