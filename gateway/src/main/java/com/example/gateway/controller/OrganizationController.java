package com.example.gateway.controller;

import com.example.gateway.client.OrganizationClient;
import com.example.gateway.model.Organization;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {
    private final OrganizationClient organizationClient;

    public OrganizationController(OrganizationClient organizationClient) {
        this.organizationClient = organizationClient;
    }

    @GetMapping("/{id}")
    public Optional<Organization> getOrganizationById(@PathVariable Long id) {
        return Optional.of(organizationClient.getOrganizationById(id));
    }

    @GetMapping
    public List<Organization> getOrganizations() {
        return organizationClient.getOrganizations();
    }

    @PostMapping
    public Organization saveOrganization(@RequestBody Organization organization) {
        return organizationClient.saveOrganization(organization);
    }

    @DeleteMapping("/{id}")
    public void deleteOrganization(@PathVariable Long id) {
        organizationClient.deleteOrganization(id);
    }
}
