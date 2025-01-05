package com.example.gateway.dto;

import com.example.gateway.model.Role;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RoleDto {
    private Long id;
    private String name;
    private String Description;
    private List<PermissionDto> permissions = new ArrayList<>();

    public static RoleDto from(Role role) {
        RoleDto dto = new RoleDto();
        dto.setId(role.getId());
        dto.setName(role.getName());
        dto.setDescription(role.getDescription());
        return dto;
    }
}
