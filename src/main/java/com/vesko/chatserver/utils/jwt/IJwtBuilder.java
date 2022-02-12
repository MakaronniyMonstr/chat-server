package com.vesko.chatserver.utils.jwt;

public interface IJwtBuilder {
    String generateToken(String username, Long expirationTimeSeconds);
}
