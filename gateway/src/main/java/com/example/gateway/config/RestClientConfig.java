package com.example.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Value("${organization.service}")
    private String organizationService;

    @Value("${permission.service}")
    private String permissionService;

    @Value("${role.service}")
    private String roleService;

    @Value("${user.service}")
    private String userService;

    @Value("${user-group.service}")
    private String userGroupService;

    @Bean
    public RestClient organizationService() {
        return RestClient.create(organizationService);
    }

    @Bean
    public RestClient permissionService() {
        return RestClient.create(permissionService);
    }

    @Bean
    public RestClient roleService() {
        return RestClient.create(roleService);
    }

    @Bean
    public RestClient userService() {
        return RestClient.create(userService);
    }

    @Bean
    public RestClient userGroupService() {
        return RestClient.create(userGroupService);
    }
}
