package com.example.gateway.controller;

import com.example.gateway.dto.ProjectReportDto;
import com.example.gateway.service.ProjectReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final ProjectReportService projectReportService;

    public ReportController(ProjectReportService projectReportService) {
        this.projectReportService = projectReportService;
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<ProjectReportDto> getProject(@PathVariable Long projectId) {
        return ResponseEntity.of(projectReportService.generateProjectReport(projectId));
    }
}
