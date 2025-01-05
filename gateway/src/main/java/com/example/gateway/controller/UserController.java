
package com.example.gateway.controller;

import com.example.gateway.client.UserClient;
import com.example.gateway.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserClient userClient;

    public UserController(UserClient userClient) {
        this.userClient = userClient;
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userClient.getUserById(id);
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return userClient.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userClient.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
