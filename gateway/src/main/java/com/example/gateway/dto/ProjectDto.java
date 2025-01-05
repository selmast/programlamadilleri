package com.example.gateway.dto;

import com.example.gateway.model.Project;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProjectDto {
    private Long id;
    private String name;
    private List<UserDto> users = new ArrayList<>();

    public static ProjectDto from(Project project) {
        ProjectDto dto = new ProjectDto();
        dto.setId(project.getId());
        dto.setName(project.getName());
        return dto;
    }
}
