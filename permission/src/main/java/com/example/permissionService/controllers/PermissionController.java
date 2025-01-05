package com.example.permissionService.controllers;

import com.example.permissionService.entity.Permission;
import com.example.permissionService.service.PermissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping
    public Permission savePermission(@RequestBody Permission permission) {
        return permissionService.savePermission(permission);
    }

    @GetMapping
    public List<Permission> getByIds(@RequestParam List<Long> ids) {
        return permissionService.getByIds(ids);
    }

    @GetMapping("/{id}")
    public Permission getPermissionById(@PathVariable Long id) {
        return permissionService.getPermissionById(id)
                .orElseThrow(() -> new RuntimeException("Permission not found!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePermissionById(@PathVariable Long id) {
        permissionService.deletePermissionById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}