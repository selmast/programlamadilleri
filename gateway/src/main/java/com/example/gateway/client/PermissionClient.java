package com.example.gateway.client;

import com.example.gateway.model.Permission;
import com.example.gateway.model.Role;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Component
public class PermissionClient {
    public static final String API_PERMISSIONS = "/api/permissions";
    private final RestClient permissionService;

    public PermissionClient(RestClient permissionService) {
        this.permissionService = permissionService;
    }

    public Permission savePermission(Permission permission) {
        return permissionService.post().uri(API_PERMISSIONS)
                .body(permission)
                .retrieve()
                .toEntity(Permission.class)
                .getBody();
    }

    public List<Permission> getByPermissionIds(List<Long> ids) {
        return permissionService.get().uri(API_PERMISSIONS + "?ids=" + StringUtils.join(ids.stream().map(String::valueOf).toList(), ','))
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public Optional<Permission> getPermissionById(Long id) {
        return Optional.ofNullable(permissionService.get().uri(API_PERMISSIONS + id).retrieve().toEntity(Permission.class).getBody());
    }

    public void deletePermissionById(Long id) {
        permissionService.delete().uri(API_PERMISSIONS + id).retrieve().toBodilessEntity();
    }
}
