package com.example.gateway.service;

import com.example.gateway.client.*;
import com.example.gateway.dto.PermissionDto;
import com.example.gateway.dto.ProjectReportDto;
import com.example.gateway.dto.RoleDto;
import com.example.gateway.dto.UserDto;
import com.example.gateway.model.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProjectReportService {
    private final OrganizationClient organizationClient;
    private final UserGroupClient userGroupClient;
    private final UserClient userClient;
    private final RoleClient roleClient;
    private final PermissionClient permissionClient;

    public ProjectReportService(OrganizationClient organizationClient, UserGroupClient userGroupClient, UserClient userClient, RoleClient roleClient, PermissionClient permissionClient) {
        this.organizationClient = organizationClient;
        this.userGroupClient = userGroupClient;
        this.userClient = userClient;
        this.roleClient = roleClient;
        this.permissionClient = permissionClient;
    }

    public Optional<ProjectReportDto> generateProjectReport(Long projectId) {
        Project project = organizationClient.getProjectById(projectId).orElseThrow(() -> new RuntimeException("project not found:" + projectId));
        ProjectReportDto dto = ProjectReportDto.from(project);
        if (null != project.getUserGroupIds() && !project.getUserGroupIds().isEmpty()) {
            // Fetch user groups
            List<UserGroup> userGroups = userGroupClient.getByUserGroupIds(project.getUserGroupIds());

            // key: userId, value: list of role ids
            Map<Long, List<Long>> userToRoleMap = new HashMap<>();
            for (UserGroup userGroup : userGroups) {
                for (Long userId : userGroup.getMemberIds()) {
                    userToRoleMap
                            .computeIfAbsent(userId, k -> new ArrayList<>())
                            .addAll(userGroup.getRoleIds());
                }
            }

            // Fetch all users in the project
            List<User> users = userClient.getByUserIds(new ArrayList<>(userToRoleMap.keySet()));

            // Map users to UserDto with roles and permissions
            List<UserDto> userDtos = new ArrayList<>();
            for (User user : users) {
                UserDto userDto = UserDto.from(user);

                List<Long> roleIds = userToRoleMap.get(user.getId());
                if (roleIds != null && !roleIds.isEmpty()) {
                    List<Role> roles = roleClient.getByRoleIds(roleIds);
                    List<RoleDto> roleDtos = new ArrayList<>();

                    for (Role role : roles) {
                        RoleDto roleDto = RoleDto.from(role);

                        // Fetch permissions for the role
                        List<Permission> permissions = permissionClient.getByPermissionIds(role.getPermissionIds().stream().toList());
                        List<PermissionDto> permissionDtos = permissions.stream()
                                .map(PermissionDto::from)
                                .toList();

                        roleDto.setPermissions(permissionDtos);
                        roleDtos.add(roleDto);
                    }

                    userDto.setRoles(roleDtos);
                }

                userDtos.add(userDto);
            }

            // Add users to the project DTO
            dto.getProject().getUsers().addAll(userDtos);
        }
        return Optional.of(dto);
    }
}
