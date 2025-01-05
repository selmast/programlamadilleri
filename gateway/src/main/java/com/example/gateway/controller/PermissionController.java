package com.example.gateway.controller;

import com.example.gateway.client.PermissionClient;
import com.example.gateway.model.Permission;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    private final PermissionClient permissionService;

    public PermissionController(PermissionClient permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping
    public Permission createPermission(@RequestBody Permission permission) {
        return permissionService.savePermission(permission);
    }

    @GetMapping("/{id}")
    public Permission getPermissionById(@PathVariable Long id) {
        return permissionService.getPermissionById(id)
                .orElseThrow(() -> new RuntimeException("Permission not found!"));
    }
}
