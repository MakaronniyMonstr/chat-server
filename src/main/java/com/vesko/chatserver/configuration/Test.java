package com.vesko.chatserver.configuration;

import com.vesko.chatserver.entity.User;
import com.vesko.chatserver.repository.TokenBoxRepository;
import com.vesko.chatserver.repository.UserRepository;
import com.vesko.chatserver.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Test implements CommandLineRunner {
    private final UserRepository userRepository;
    private final TokenBoxRepository tokenBoxRepository;
    private final IUserService userService;

    @Autowired
    public Test(UserRepository userRepository, TokenBoxRepository tokenBoxRepository, IUserService userService) {
        this.userRepository = userRepository;
        this.tokenBoxRepository = tokenBoxRepository;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
