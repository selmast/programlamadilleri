
package com.example.organizationService.controllers;

import com.example.organizationService.entity.Organization;
import com.example.organizationService.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {
    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping
    public Organization createOrganization(@RequestBody Organization organization) {
        return organizationService.saveOrganization(organization);
    }

    @GetMapping("/{id}")
    public Optional<Organization> getOrganizationById(@PathVariable Long id) {
        return organizationService.getOrganizationById(id);
    }

    @GetMapping
    public List<Organization> getOrganizations() {
        return organizationService.getOrganizations();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganization(@PathVariable Long id) {
        organizationService.deleteOrganization(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
