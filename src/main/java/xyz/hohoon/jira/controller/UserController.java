package xyz.hohoon.jira.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.hohoon.jira.dto.User;
import xyz.hohoon.jira.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/v1/join")
    public Long join(HttpServletRequest request, HttpServletResponse response, @RequestBody User.Request userRequest) {
        return userService.joinMember(userRequest.getUsername(), userRequest.getPassword());
    }

    @PostMapping("/v1/login")
    public void login(HttpServletRequest request, HttpServletResponse response, @RequestBody User.Request userRequest) {
        String token = userService.loginMember(userRequest.getUsername(), userRequest.getPassword());
        response.setHeader("X-AUTH-TOKEiN", token);
    }
}
