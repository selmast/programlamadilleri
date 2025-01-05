package com.example.gateway.model;

import lombok.Data;

import java.util.Set;

@Data
public class Organization {
    private Long id;
    private String name;
    private Set<Project> projects;
}
