package xyz.hohoon.jira.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.hohoon.jira.security.JwtTokenProvider;
import xyz.hohoon.jira.security.user.User;
import xyz.hohoon.jira.security.user.UserRepository;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService{
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;
    private UserRepository userRepository;

    UserServiceImpl(PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    @Override
    public long joinMember(String username, String email, String password) {
        if (isUserExist(username)) {
            throw new IllegalArgumentException("user already exist");
        }
        return userRepository.save(User.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
                .roles(Collections.singletonList("ROLE_USER"))
                .build()).getId();
    }

    @Override
    public String loginMember(String username, String password) {
        if (!isUserExist(username)) {
            throw new UsernameNotFoundException("user not exist");
        }
        User user = userRepository.findByUsername(username).get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("wrong password");
        }
        return jwtTokenProvider.generateToken(username);
    }

    private boolean isUserExist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
