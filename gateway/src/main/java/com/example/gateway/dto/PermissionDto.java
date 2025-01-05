package com.example.gateway.dto;

import com.example.gateway.model.Permission;
import lombok.Data;

@Data
public class PermissionDto {
    private Long id;
    private String name;
    private String description;

    public static PermissionDto from(Permission permission) {
        PermissionDto dto = new PermissionDto();
        dto.setId(permission.getId());
        dto.setName(permission.getName());
        dto.setDescription(permission.getDescription());
        return dto;
    }
}
