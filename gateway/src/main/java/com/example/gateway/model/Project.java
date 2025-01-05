package com.example.gateway.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Project {
    private Long id;
    private String name;
    private Organization organization;
    private List<Long> userGroupIds = new ArrayList<>();
}
