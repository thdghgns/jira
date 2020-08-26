package xyz.hohoon.jira.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class ProjectDto {
    @Getter
    public static class Request {
        @NotBlank
        private String key;
        @NotBlank
        private String name;
        private String description;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Response {
        private String key;
        private String name;
        private String description;
    }
}
