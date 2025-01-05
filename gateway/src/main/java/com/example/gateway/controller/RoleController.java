package com.example.gateway.controller;

import com.example.gateway.client.RoleClient;
import com.example.gateway.model.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleClient roleClient;

    public RoleController(RoleClient roleClient) {
        this.roleClient = roleClient;
    }

    @PostMapping
    public Role saveRole(@RequestBody Role role) {
        return roleClient.saveRole(role);
    }

    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable Long id) {
        return roleClient.getRoleById(id)
                .orElseThrow(() -> new RuntimeException("Role not found!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoleById(@PathVariable Long id) {
        roleClient.deleteRoleById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/{roleId}/permissions/{permissionId}")
    public ResponseEntity<Role> addPermissionToRole(@PathVariable Long roleId, @PathVariable Long permissionId) {
        Role updatedRole = roleClient.addPermissionToRole(roleId, permissionId);
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("/{roleId}/permissions/{permissionId}")
    public ResponseEntity<Role> removePermissionFromRole(@PathVariable Long roleId, @PathVariable Long permissionId) {
        Role updatedRole = roleClient.removePermissionFromRole(roleId, permissionId);
        return ResponseEntity.ok(updatedRole);
    }
}