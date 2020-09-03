package xyz.hohoon.jira.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import xyz.hohoon.jira.code.IssuePriority;
import xyz.hohoon.jira.entity.Component;
import xyz.hohoon.jira.entity.Issue;
import xyz.hohoon.jira.entity.Project;
import xyz.hohoon.jira.entity.Version;
import xyz.hohoon.jira.repository.IssueRepository;

import java.util.Date;

@Service
public class IssueServiceImpl implements IssueService {
    @Autowired
    ProjectService projectService;
    @Autowired
    ComponentService componentService;
    @Autowired
    VersionService versionService;
    @Autowired
    UserService userService;
    @Autowired
    IssueRepository issueRepository;

    @Override
    public String createIssue(String projectKey, String summary, IssuePriority priority, Date dueDate,
                            String componentKey, Long versionId, String assigneeUsername, String description) {
        Project project = projectService.getProjectOrThrowException(projectKey);
        Component component = componentService.getComponentOrThrowException(componentKey);
        Version version = versionService.getVersionOrThrowException(versionId);
        String issueKey = projectKey + projectService.increaseAndGetProjectIssueCount(projectKey);
        Issue.IssueBuilder issueBuilder = Issue.builder()
                .key(issueKey)
                .project(project)
                .component(component)
                .fixVersion(version)
                .summary(summary)
                .description(description)
                .priority(priority)
                .reporter(userService.getCurrentUser());

        if (!StringUtils.isEmpty(assigneeUsername)) {
            issueBuilder.assignee(userService.getUserOrThrowException(assigneeUsername));
        }

        if (!ObjectUtils.isEmpty(dueDate)) {
            issueBuilder.dueDate(dueDate);
        }

        Issue issue = issueRepository.save(issueBuilder.build());

        return issue.getKey();
    }
}