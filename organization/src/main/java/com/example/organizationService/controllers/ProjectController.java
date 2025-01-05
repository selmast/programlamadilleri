package com.example.organizationService.controllers;

import com.example.organizationService.entity.Project;
import com.example.organizationService.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("/organization/{organizationId}")
    public ResponseEntity<Project> createProject(@PathVariable Long organizationId, @RequestBody Project project) {
        Project createdProject = projectService.createProject(organizationId, project);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProject(@PathVariable Long id) {
        return ResponseEntity.of(projectService.getProjectById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/{id}/userGroups/{groupId}")
    public ResponseEntity<Void> addUserGroupToProject(@PathVariable Long id, @PathVariable Long groupId) {
        projectService.addUserGroupToProject(id, groupId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}/userGroups/{groupId}")
    public ResponseEntity<Void> removeUserGroupToProject(@PathVariable Long id, @PathVariable Long groupId) {
        projectService.removeUserGroupFromProject(id, groupId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}