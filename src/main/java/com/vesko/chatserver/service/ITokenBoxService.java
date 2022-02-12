package com.vesko.chatserver.service;

import com.vesko.chatserver.entity.TokenBox;
import com.vesko.chatserver.entity.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ITokenBoxService {
    TokenBox obtainTokenBox(String username);
    TokenBox refreshTokenBox(String refreshToken);
    void clearTokenBox(String refreshToken);

    @Transactional(readOnly = true)
    User validateTokenAndGetUser(String accessToken);
}
