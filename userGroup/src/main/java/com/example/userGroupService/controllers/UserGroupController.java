
package com.example.userGroupService.controllers;

import com.example.userGroupService.entity.UserGroup;
import com.example.userGroupService.service.UserGroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-groups")
public class UserGroupController {
    private final UserGroupService userGroupService;

    public UserGroupController(UserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }

    @PostMapping
    public UserGroup createUserGroup(@RequestBody UserGroup userGroup) {
        return userGroupService.saveUserGroup(userGroup);
    }

    @PostMapping("/{groupId}/roles/{roleId}")
    public String assignRoleToGroup(@PathVariable Long groupId, @PathVariable Long roleId) {
        userGroupService.assignRoleToGroup(groupId, roleId);
        return "Role assigned to group successfully!";
    }

    @GetMapping("/{id}")
    public Optional<UserGroup> getUserGroupById(@PathVariable Long id) {
        return userGroupService.getUserGroupById(id);
    }

    @GetMapping
    public List<UserGroup> getByUserGroupIds(@RequestParam List<Long> groupIds) {
        return userGroupService.getByUserGroupIds(groupIds);
    }

    @PostMapping("/{groupId}/users/{userId}")
    public ResponseEntity<String> addUserToGroup(@PathVariable Long groupId, @PathVariable Long userId) {
        userGroupService.addUserToGroup(groupId, userId);
        return ResponseEntity.ok("User added to group successfully!");
    }
}
