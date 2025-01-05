
package com.example.organizationService.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserGroup {
    private Long id;

    private String name;

    private Set<Long> memberIds = new HashSet<>();

    private Set<Long> roleIds = new HashSet<>();
}
