package xyz.hohoon.jira.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class User {
    @Getter
    public static class SignUpRequest {
        private String username;
        private String email;
        private String password;
    }

    @Getter
    public static class SignInRequest {
        private String username;
        private String password;
    }

    @AllArgsConstructor
    @Getter
    public static class SignInResponse {
        private String username;
        private String accessToken;
    }
}
