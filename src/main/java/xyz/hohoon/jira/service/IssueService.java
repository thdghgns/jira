package xyz.hohoon.jira.service;

import xyz.hohoon.jira.code.IssuePriority;

import java.util.Date;

public interface IssueService {
    String createIssue(String projectKey, String summary, IssuePriority priority, Date dueDate,
                       String componentKey, Long versionId, String assigneeUsername, String description);
}
