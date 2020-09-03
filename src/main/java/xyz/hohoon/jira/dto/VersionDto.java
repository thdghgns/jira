package xyz.hohoon.jira.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class VersionDto {
    @Getter
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private String name;
    }
}
