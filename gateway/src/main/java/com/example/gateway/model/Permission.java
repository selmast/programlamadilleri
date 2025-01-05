package com.example.gateway.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Permission {
    private Long id;
    private String name;
    private String description;
}