package xyz.hohoon.jira.service;

public interface UserService {
    public long joinMember(String username, String password);
    public String loginMember(String username, String password);
}
