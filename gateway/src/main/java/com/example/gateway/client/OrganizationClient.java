package com.example.gateway.client;

import com.example.gateway.model.Organization;
import com.example.gateway.model.Project;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Component
public class OrganizationClient {
    public static final String API_PROJECTS = "/api/projects";
    public static final String API_ORGANIZATIONS = "/api/organizations";
    private final RestClient organizationService;

    public OrganizationClient(RestClient organizationService) {
        this.organizationService = organizationService;
    }

    public Organization getOrganizationById(Long id) {
        return organizationService.get().uri(API_ORGANIZATIONS + "/" + id).retrieve().toEntity(Organization.class).getBody();
    }

    public List<Organization> getOrganizations() {
        return organizationService.get().uri(API_ORGANIZATIONS)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public Organization saveOrganization(Organization organization) {
        return organizationService.post().uri(API_ORGANIZATIONS)
                .body(organization)
                .retrieve()
                .toEntity(Organization.class)
                .getBody();
    }

    public void deleteOrganization(Long id) {
        organizationService.delete().uri(API_ORGANIZATIONS + "/" + id)
                .retrieve().toBodilessEntity();
    }

    public Project saveProject(Long organizationId, Project project) {
        return organizationService.post().uri("/api/projects/organization/" + organizationId)
                .body(project)
                .retrieve()
                .toEntity(Project.class)
                .getBody();
    }

    public Optional<Project> getProjectById(Long id) {
        return Optional.ofNullable(organizationService.get().uri(API_PROJECTS + "/" + id).retrieve().toEntity(Project.class).getBody());
    }

    public void deleteProjectById(Long id) {
        organizationService.delete().uri(API_PROJECTS + "/" + id).retrieve().toBodilessEntity();
    }

    public void addUserGroupToProject(Long projectId, Long groupId) {
        organizationService.post().uri(API_PROJECTS + "/" + projectId + "/userGroups/"+ groupId)
                .retrieve()
                .toBodilessEntity();
    }

    public void removeUserGroupFromProject(Long projectId, Long groupId) {
        organizationService.delete().uri(API_PROJECTS + "/" + projectId + "/userGroups/"+ groupId)
                .retrieve()
                .toBodilessEntity();
    }
}
