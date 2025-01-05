
package com.example.gateway.controller;

import com.example.gateway.client.UserGroupClient;
import com.example.gateway.model.UserGroup;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user-groups")
public class UserGroupController {
    private final UserGroupClient userGroupClient;

    public UserGroupController(UserGroupClient userGroupClient) {
        this.userGroupClient = userGroupClient;
    }

    @PostMapping
    public UserGroup createUserGroup(@RequestBody UserGroup userGroup) {
        return userGroupClient.saveUserGroup(userGroup);
    }

    @PostMapping("/{groupId}/roles/{roleId}")
    public String assignRoleToGroup(@PathVariable Long groupId, @PathVariable Long roleId) {
        userGroupClient.assignRoleToGroup(groupId, roleId);
        return "Role assigned to group successfully!";
    }

    @GetMapping("/{id}")
    public Optional<UserGroup> getUserGroupById(@PathVariable Long id) {
        return userGroupClient.getUserGroupById(id);
    }

    @PostMapping("/{groupId}/users/{userId}")
    public ResponseEntity<String> addUserToGroup(@PathVariable Long groupId, @PathVariable Long userId) {
        userGroupClient.addUserToGroup(groupId, userId);
        return ResponseEntity.ok("User added to group successfully!");
    }
}
