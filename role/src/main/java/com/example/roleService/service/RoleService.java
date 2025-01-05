package com.example.roleService.service;

import com.example.roleService.client.PermissionClient;
import com.example.roleService.entity.Role;
import com.example.roleService.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final PermissionClient permissionClient;

    public RoleService(RoleRepository roleRepository, PermissionClient permissionClient) {
        this.roleRepository = roleRepository;
        this.permissionClient = permissionClient;
    }

    @Transactional
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    @Transactional
    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }

    @Transactional
    public Role addPermissionToRole(Long roleId, Long permissionId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + roleId));
        permissionClient.getPermissionById(permissionId)
                .orElseThrow(() -> new RuntimeException("Permission not found with id: " + permissionId));

        role.getPermissionIds().add(permissionId);
        return roleRepository.save(role);
    }

    @Transactional
    public Role removePermissionFromRole(Long roleId, Long permissionId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + roleId));
        permissionClient.getPermissionById(permissionId)
                .orElseThrow(() -> new RuntimeException("Permission not found with id: " + permissionId));

        role.getPermissionIds().remove(permissionId);
        return roleRepository.save(role);
    }

    public List<Role> getByIds(List<Long> ids) {
        return roleRepository.findAllById(ids);
    }
}