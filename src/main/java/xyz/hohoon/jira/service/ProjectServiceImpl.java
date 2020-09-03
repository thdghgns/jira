package xyz.hohoon.jira.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.hohoon.jira.dto.ProjectDto;
import xyz.hohoon.jira.entity.Project;
import xyz.hohoon.jira.exception.ApplicationException;
import xyz.hohoon.jira.repository.ProjectRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    @Override
    public List<ProjectDto.Response> getAllProjects() {
        List<Project> projectPage = projectRepository.findAll();

        return projectPage.stream()
                .map(p -> new ProjectDto.Response(p.getKey(), p.getName(), p.getDescription()))
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDto.Response findProject(String projectKey) {
        Project project = getProjectOrThrowException(projectKey);
        return new ProjectDto.Response(project.getKey(), project.getName(), project.getDescription());
    }

    @Override
    public Project getProjectOrThrowException(String projectKey) {
        Project project = projectRepository.findById(projectKey)
                .orElseThrow(() -> new ApplicationException.ResourceNotFoundException("project not found"));
        return project;
    }

    @Override
    public ProjectDto.Response createProject(ProjectDto.Request request) {
        Project project = projectRepository.save(new Project(request.getKey(), request.getName(), request.getDescription()));
        return new ProjectDto.Response(project.getKey(), project.getName(), project.getDescription());
    }

    @Override
    public ProjectDto.Response updateProject(ProjectDto.Request request) {
        ProjectDto.Response response = new ProjectDto.Response();

        projectRepository.findById(request.getKey())
                .ifPresent(p -> {
                    p.setName(request.getName());
                    p.setDescription(request.getDescription());

                    Project updatedProject = projectRepository.save(p);
                    response.setKey(updatedProject.getKey());
                    response.setName(updatedProject.getName());
                    response.setDescription(updatedProject.getDescription());
                });

        return response;
    }

    @Override
    public void deleteProject(String projectKey) {
        if (projectRepository.existsById(projectKey)) {
            projectRepository.deleteById(projectKey);
        } else {
            throw new ApplicationException.ResourceNotFoundException("project not found");
        }
    }

    @Transactional
    @Override
    public long increaseAndGetProjectIssueCount(String projectKey) {
        Project project = getProjectOrThrowException(projectKey);
        project.setCount(project.getCount() +1);
        projectRepository.save(project);

        return project.getCount();
    }
}
