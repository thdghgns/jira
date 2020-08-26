package xyz.hohoon.jira.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.hohoon.jira.dto.ProjectDto;
import xyz.hohoon.jira.service.ProjectService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/api/project")
    public List<ProjectDto.Response> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/api/project/{projectKey}")
    public ProjectDto.Response findProject(@PathVariable(name = "projectKey") String projectKey) {
        return projectService.findProject(projectKey);
    }

    @PostMapping("/api/project")
    public ProjectDto.Response createProject(@Valid @RequestBody ProjectDto.Request request) {
        return projectService.createProject(request);
    }

    @PutMapping("/api/project")
    public ProjectDto.Response updateProject(@Valid @RequestBody ProjectDto.Request request) {
        return projectService.updateProject(request);
    }

    @DeleteMapping("/api/project/{projectKey}")
    public void deleteProject(@PathVariable(name = "projectKey") String projectKey) {
        projectService.deleteProject(projectKey);
    }
}
