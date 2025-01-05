package com.example.permissionService.service;

import com.example.permissionService.entity.Permission;
import com.example.permissionService.repository.PermissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService {
    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Transactional
    public Permission savePermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    public Optional<Permission> getPermissionById(Long id) {
        return permissionRepository.findById(id);
    }

    public List<Permission> getByIds(List<Long> ids) {
        return permissionRepository.findAllById(ids);
    }

    @Transactional
    public void deletePermissionById(Long id) {
        permissionRepository.deleteById(id);
    }
}