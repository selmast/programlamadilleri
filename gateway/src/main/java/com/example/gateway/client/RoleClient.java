package com.example.gateway.client;

import com.example.gateway.model.Role;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Component
public class RoleClient {
    public static final String API_ROLES = "/api/roles";
    private final RestClient roleService;

    public RoleClient(RestClient roleService) {
        this.roleService = roleService;
    }

    public Role saveRole(Role role) {
        return roleService.post().uri(API_ROLES)
                .body(role)
                .retrieve()
                .toEntity(Role.class)
                .getBody();
    }

    public Optional<Role> getRoleById(Long id) {
        return Optional.ofNullable(roleService.get().uri(API_ROLES + "/" + id).retrieve().toEntity(Role.class).getBody());
    }

    public void deleteRoleById(Long id) {
        roleService.delete().uri(API_ROLES + id).retrieve().toBodilessEntity();
    }

    public Role addPermissionToRole(Long roleId, Long permissionId) {
        return roleService.post()
                .uri(API_ROLES + "/" + roleId + "/permissions/" + permissionId)
                .retrieve()
                .toEntity(Role.class)
                .getBody();
    }

    public Role removePermissionFromRole(Long roleId, Long permissionId) {
        return roleService.delete()
                .uri(API_ROLES + "/" + roleId + "/permissions/" + permissionId)
                .retrieve()
                .toEntity(Role.class)
                .getBody();
    }

    public List<Role> getByRoleIds(List<Long> ids) {
        return roleService.get().uri(API_ROLES + "?ids=" + StringUtils.join(ids.stream().map(String::valueOf).toList(), ','))
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}
