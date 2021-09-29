package com.vesko.chatserver.service;

import com.vesko.chatserver.entity.TokenBox;
import com.vesko.chatserver.entity.User;
import com.vesko.chatserver.exception.TokenValidationException;
import com.vesko.chatserver.repository.TokenBoxRepository;
import com.vesko.chatserver.utils.jwt.JwtBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenBoxService implements ITokenBoxService {
    private final TokenBoxRepository tokenBoxRepository;
    private final IUserService userService;
    private final JwtBuilder jwtBuilder;

    @Value("${spring.jwt.access_expires}")
    private Long accessExpiresTime;

    @Value("${spring.jwt.refresh_expires}")
    private Long refreshExpiresTime;

    @Autowired
    public TokenBoxService(TokenBoxRepository tokenBoxRepository, IUserService userService, JwtBuilder jwtBuilder) {
        this.tokenBoxRepository = tokenBoxRepository;
        this.userService = userService;
        this.jwtBuilder = jwtBuilder;
    }

    @Override
    public TokenBox obtainTokenBox(String username) {
        User user = userService.findUserByUsername(username);
        TokenBox tokenBox = user.getTokenBox();

        tokenBox.setAccess(createAccessToken(username));
        tokenBox.setRefresh(createRefreshToken(username));

        return tokenBox;
    }

    @Override
    public TokenBox refreshTokenBox(String refreshToken) {
        String username = jwtBuilder.validateTokenAndGetClaims(refreshToken).getSubject();
        tokenBoxRepository.findTokenBoxByUserUsernameAndRefresh(username, refreshToken)
                .orElseThrow(() -> new TokenValidationException(username));

        return obtainTokenBox(username);
    }

    @Override
    public User validateTokenAndGetUser(String accessToken) {
        String username = jwtBuilder.validateTokenAndGetClaims(accessToken).getSubject();
        TokenBox tokenBox = tokenBoxRepository.findTokenBoxByUserUsernameAndAccess(username, accessToken)
                .orElseThrow(() -> new TokenValidationException(username));

        return tokenBox.getUser();
    }

    private String createRefreshToken(String username) {
        return jwtBuilder.generateToken(username, refreshExpiresTime);
    }

    private String createAccessToken(String username) {
        return jwtBuilder.generateToken(username, accessExpiresTime);
    }
}
