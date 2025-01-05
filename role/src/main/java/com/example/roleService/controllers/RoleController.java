package com.example.roleService.controllers;

import com.example.roleService.entity.Role;
import com.example.roleService.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public Role saveRole(@RequestBody Role role) {
        return roleService.saveRole(role);
    }

    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id)
                .orElseThrow(() -> new RuntimeException("Role not found!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoleById(@PathVariable Long id) {
        roleService.deleteRoleById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/{roleId}/permissions/{permissionId}")
    public ResponseEntity<Role> addPermissionToRole(@PathVariable Long roleId, @PathVariable Long permissionId) {
        Role updatedRole = roleService.addPermissionToRole(roleId, permissionId);
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("/{roleId}/permissions/{permissionId}")
    public ResponseEntity<Role> removePermissionFromRole(@PathVariable Long roleId, @PathVariable Long permissionId) {
        Role updatedRole = roleService.removePermissionFromRole(roleId, permissionId);
        return ResponseEntity.ok(updatedRole);
    }

    @GetMapping
    public List<Role> getByIds(@RequestParam List<Long> ids) {
        return roleService.getByIds(ids);
    }
}