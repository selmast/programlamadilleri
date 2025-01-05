package com.example.organizationService.service;

import com.example.organizationService.client.UserGroupClient;
import com.example.organizationService.entity.Organization;
import com.example.organizationService.entity.Project;
import com.example.organizationService.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final OrganizationService organizationService;
    private final UserGroupClient userGroupClient;

    public ProjectService(ProjectRepository projectRepository, OrganizationService organizationService, UserGroupClient userGroupClient) {
        this.projectRepository = projectRepository;
        this.organizationService = organizationService;
        this.userGroupClient = userGroupClient;
    }

    @Transactional
    public Project createProject(Long organizationId, Project project) {
        Organization organization = organizationService.getOrganizationById(organizationId)
                .orElseThrow(() -> new IllegalArgumentException("Organization not found"));

        project.setOrganization(organization);
        return projectRepository.save(project);
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    @Transactional
    public void deleteProject(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with ID: " + projectId));
        Organization organization = project.getOrganization();
        if (organization != null) {
            organization.getProjects().remove(project);
        }
        projectRepository.delete(project);
    }

    @Transactional
    public void addUserGroupToProject(Long projectId, Long groupId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with ID: " + projectId));

        userGroupClient.getUserGroupById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found!"));
        project.getUserGroupIds().add(groupId);
        projectRepository.save(project);
    }

    @Transactional
    public void removeUserGroupFromProject(Long projectId, Long groupId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with ID: " + projectId));

        userGroupClient.getUserGroupById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found!"));
        project.getUserGroupIds().add(groupId);
        projectRepository.save(project);
    }
}