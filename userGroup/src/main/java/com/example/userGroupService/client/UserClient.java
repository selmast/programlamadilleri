package com.example.userGroupService.client;

import com.example.userGroupService.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Component
public class UserClient {
    public static final String API_USERS = "/api/users";
    private final RestClient userService;

    public UserClient(RestClient userService) {
        this.userService = userService;
    }

    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(userService.get().uri(API_USERS + "/" + id).retrieve().toEntity(User.class).getBody());
    }
}
