package com.example.gateway.client;

import com.example.gateway.model.UserGroup;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Component
public class UserGroupClient {
    public static final String API_USER_GROUP = "/api/user-groups";
    private final RestClient userGroupService;

    public UserGroupClient(RestClient userGroupService) {
        this.userGroupService = userGroupService;
    }

    public UserGroup saveUserGroup(UserGroup userGroup) {
        return userGroupService.post().uri(API_USER_GROUP)
                .body(userGroup)
                .retrieve()
                .toEntity(UserGroup.class)
                .getBody();
    }

    public void assignRoleToGroup(Long groupId, Long roleId) {
        userGroupService.post().uri(API_USER_GROUP + "/" + groupId + "/roles/"+ roleId)
                .retrieve()
                .toBodilessEntity();
    }

    public Optional<UserGroup> getUserGroupById(Long id) {
        return Optional.ofNullable(userGroupService.get().uri(API_USER_GROUP + "/" + id)
                .retrieve()
                .toEntity(UserGroup.class)
                .getBody());
    }

    public List<UserGroup> getByUserGroupIds(List<Long> ids) {
        return userGroupService.get().uri(API_USER_GROUP + "?groupIds=" + StringUtils.join(ids.stream().map(String::valueOf).toList(), ','))
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public void addUserToGroup(Long groupId, Long userId) {
        userGroupService.post().uri(API_USER_GROUP + "/" + groupId + "/users/"+ userId)
                .retrieve()
                .toBodilessEntity();
    }
}
