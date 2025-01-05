package com.example.userGroupService.client;

import com.example.userGroupService.model.Role;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Component
public class RoleClient {
    public static final String API_ROLES = "/api/roles";
    private final RestClient roleService;

    public RoleClient(RestClient roleService) {
        this.roleService = roleService;
    }

    public Optional<Role> getRoleById(Long id) {
        return Optional.ofNullable(roleService.get().uri(API_ROLES + "/" + id).retrieve().toEntity(Role.class).getBody());
    }
}
