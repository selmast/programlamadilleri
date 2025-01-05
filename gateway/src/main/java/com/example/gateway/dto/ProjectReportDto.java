package com.example.gateway.dto;

import com.example.gateway.model.Project;
import lombok.Data;

@Data
public class ProjectReportDto {
    private ProjectDto project;

    public static ProjectReportDto from(Project project) {
        ProjectReportDto dto = new ProjectReportDto();
        dto.setProject(ProjectDto.from(project));
        return dto;
    }
}
