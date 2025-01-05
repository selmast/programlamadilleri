package com.example.userGroupService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Value("${role.service}")
    private String roleService;

    @Value("${user.service}")
    private String userService;

    @Bean
    public RestClient roleService() {
        return RestClient.create(roleService);
    }

    @Bean
    public RestClient userService() {
        return RestClient.create(userService);
    }
}
