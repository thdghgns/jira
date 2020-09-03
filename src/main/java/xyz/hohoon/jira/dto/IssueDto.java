package xyz.hohoon.jira.dto;

import lombok.Getter;
import xyz.hohoon.jira.code.IssuePriority;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

public class IssueDto {
    @Getter
    public static class Create {
        @NotBlank
        private String projectKey;
        @NotBlank
        private String summary;
        @NotBlank
        private IssuePriority priority;
        private Date dueDate;
        @NotBlank
        private String componentKey;
        @NotBlank
        @Min(value = 0)
        private Long fixVersionId;
        private String assigneeUsername;
        @NotBlank
        private String description;
    }
}
