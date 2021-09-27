package com.vesko.chatserver.service;

import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;

@Transactional()
public interface ITokenService {
    void refreshToken(String refreshToken);

    @Transactional(readOnly = true)
    void authenticateToken(String accessToken) throws AuthenticationException;
}
