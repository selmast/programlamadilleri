package com.example.roleService.client;

import com.example.roleService.model.Permission;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Component
public class PermissionClient {
    private final RestClient permissionService;

    public PermissionClient(RestClient permissionService) {
        this.permissionService = permissionService;
    }

    public Optional<Permission> getPermissionById(Long id) {
        return Optional.ofNullable(permissionService.get().uri("/api/permissions/" + id).retrieve().toEntity(Permission.class).getBody());
    }
}
