package com.example.gateway.client;

import com.example.gateway.model.User;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
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

    public User saveUser(User role) {
        return userService.post().uri(API_USERS)
                .body(role)
                .retrieve()
                .toEntity(User.class)
                .getBody();
    }

    public void deleteUserById(Long id) {
        userService.delete().uri(API_USERS + id).retrieve().toBodilessEntity();
    }

    public List<User> getByUserIds(List<Long> ids) {
        return userService.get().uri(API_USERS + "?ids=" + StringUtils.join(ids.stream().map(String::valueOf).toList(), ','))
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}
