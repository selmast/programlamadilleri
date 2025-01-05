
package com.example.gateway.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Role {
    private Long id;
    private String name;
    private String Description;
    private Set<Long> permissionIds = new HashSet<>();
}
