package com.vesko.chatserver.service;

import com.vesko.chatserver.entity.TokenBox;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;

@Transactional
public interface ITokenBoxService {
    TokenBox obtainTokenBox(String username);
    TokenBox refreshTokenBox(String refreshToken);
    void validateToken(String accessToken) throws AuthenticationException;
}
