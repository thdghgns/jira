package xyz.hohoon.jira.service;

import xyz.hohoon.jira.dto.ProjectDto;
import xyz.hohoon.jira.entity.Project;

import java.util.List;

public interface ProjectService {
    List<ProjectDto.Response> getAllProjects();
    ProjectDto.Response findProject(String projectKey);
    Project getProjectOrThrowException(String projectKey);
    ProjectDto.Response createProject(ProjectDto.Request request);
    ProjectDto.Response updateProject(ProjectDto.Request request);
    void deleteProject(String projectKey);
    long increaseAndGetProjectIssueCount(String projectKey);
}
