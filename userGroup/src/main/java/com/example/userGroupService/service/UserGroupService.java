
package com.example.userGroupService.service;

import com.example.userGroupService.client.RoleClient;
import com.example.userGroupService.client.UserClient;
import com.example.userGroupService.entity.UserGroup;
import com.example.userGroupService.repository.UserGroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserGroupService {
    private final UserGroupRepository userGroupRepository;
    private final RoleClient roleClient;
    private final UserClient userClient;

    public UserGroupService(UserGroupRepository userGroupRepository, RoleClient roleClient, UserClient userClient) {
        this.userGroupRepository = userGroupRepository;
        this.roleClient = roleClient;
        this.userClient = userClient;
    }

    @Transactional
    public UserGroup saveUserGroup(UserGroup userGroup) {
        return userGroupRepository.save(userGroup);
    }

    public Optional<UserGroup> getUserGroupById(Long userGroupId) {
        return userGroupRepository.findById(userGroupId);
    }

    public List<UserGroup> getByUserGroupIds(List<Long> userGroupIds) {
        return userGroupRepository.findAllById(userGroupIds);
    }

    @Transactional
    public void assignRoleToGroup(Long groupId, Long roleId) {
        UserGroup group = userGroupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found!"));
        roleClient.getRoleById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found!"));

        group.getRoleIds().add(roleId);
        userGroupRepository.save(group);
    }

    @Transactional
    public void addUserToGroup(Long groupId, Long userId) {
        UserGroup group = userGroupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found!"));
        userClient.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        group.getMemberIds().add(userId);
        userGroupRepository.save(group);
    }
}
