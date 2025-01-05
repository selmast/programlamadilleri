package com.example.organizationService.client;

import com.example.organizationService.model.UserGroup;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Component
public class UserGroupClient {
    public static final String API_USER_GROUP = "/api/user-groups";
    private final RestClient userGroupService;

    public UserGroupClient(RestClient userGroupService) {
        this.userGroupService = userGroupService;
    }

    public Optional<UserGroup> getUserGroupById(Long id) {
        return Optional.ofNullable(userGroupService.get().uri(API_USER_GROUP + "/" + id)
                .retrieve()
                .toEntity(UserGroup.class)
                .getBody());
    }
}
