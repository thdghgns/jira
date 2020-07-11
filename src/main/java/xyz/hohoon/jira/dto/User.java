package xyz.hohoon.jira.dto;

import lombok.Getter;

public class User {
    @Getter
    public static class Request {
        private String username;
        private String password;
    }
}
