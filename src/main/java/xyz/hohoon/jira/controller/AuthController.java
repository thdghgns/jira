package xyz.hohoon.jira.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.hohoon.jira.dto.User;
import xyz.hohoon.jira.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("/api/auth/signup")
    public Long signUp(HttpServletRequest request, HttpServletResponse response, @RequestBody User.SignUpRequest signUpRequest) {
        return userService.joinMember(signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getPassword());
    }

    @PostMapping("/api/auth/signin")
    public User.SignInResponse signIn(HttpServletRequest request, HttpServletResponse response, @RequestBody User.SignInRequest signInRequest) {
        String accessToken = userService.loginMember(signInRequest.getUsername(), signInRequest.getPassword());
//        response.setHeader("X-AUTH-TOKEN", accessToken);
        return new User.SignInResponse(signInRequest.getUsername(), accessToken);
    }
}
