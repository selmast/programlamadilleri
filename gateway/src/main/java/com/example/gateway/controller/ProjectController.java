package com.example.gateway.controller;

import com.example.gateway.client.OrganizationClient;
import com.example.gateway.model.Project;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final OrganizationClient organizationClient;

    public ProjectController(OrganizationClient organizationClient) {
        this.organizationClient = organizationClient;
    }

    @PostMapping("/organization/{organizationId}")
    public ResponseEntity<Project> saveProject(@PathVariable Long organizationId, @RequestBody Project project) {
        Project createdProject = organizationClient.saveProject(organizationId, project);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProject(@PathVariable Long id) {
        return ResponseEntity.of(organizationClient.getProjectById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        organizationClient.deleteProjectById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/{id}/user-groups/{groupId}")
    public void addUserGroupToProject(@PathVariable Long id, @PathVariable Long groupId) {
        organizationClient.addUserGroupToProject(id, groupId);
    }

    @DeleteMapping("/{id}/user-groups/{groupId}")
    public void removeUserGroupToProject(@PathVariable Long id, @PathVariable Long groupId) {
        organizationClient.removeUserGroupFromProject(id, groupId);
    }
}
