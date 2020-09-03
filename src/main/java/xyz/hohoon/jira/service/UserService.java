package xyz.hohoon.jira.service;

import xyz.hohoon.jira.security.user.User;

public interface UserService {
    long joinMember(String username, String email, String password);
    String loginMember(String username, String password);
    User getUserOrThrowException(String username);
    User getCurrentUser();
}
