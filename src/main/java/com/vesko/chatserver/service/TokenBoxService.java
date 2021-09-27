package com.vesko.chatserver.service;

import com.vesko.chatserver.entity.TokenBox;
import com.vesko.chatserver.repository.TokenBoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class TokenBoxService implements ITokenBoxService {
    private final TokenBoxRepository tokenBoxRepository;
    private final IUserService userService;

    @Autowired
    public TokenBoxService(TokenBoxRepository tokenBoxRepository, IUserService userService) {
        this.tokenBoxRepository = tokenBoxRepository;
        this.userService = userService;
    }

    @Override
    public TokenBox obtainTokenBox(String username) {
        userService.findUserByUsername(username);
        return null;
    }

    @Override
    public TokenBox refreshTokenBox(String refreshToken) {
        return null;
    }

    @Override
    public void validateToken(String accessToken) throws AuthenticationException {

    }
}
