package com.vesko.chatserver.utils.jwt;

import com.vesko.chatserver.exception.TokenValidationException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtBuilder {
    private final byte[] secret;
    private final SigningKeyResolver signingKeyResolver;

    public JwtBuilder(@Value("${spring.secret}") String secret) {
        this.secret = secret.getBytes(StandardCharsets.UTF_8);
        this.signingKeyResolver = new SigningKeyResolverAdapter() {
            @Override
            public byte[] resolveSigningKeyBytes(JwsHeader header, Claims claims) {
                return secret.getBytes(StandardCharsets.UTF_8);
            }
        };
    }

    /*
    Generates new token with username as subject and expiration time
    */
    public String generateToken(String username, Long expirationTimeSeconds) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(Date.from(Instant.now().plus(expirationTimeSeconds, ChronoUnit.SECONDS)))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    /*
    Validates token if valid returns claims else throws TokenValidationException()
     */
    public Claims validateTokenAndGetClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKeyResolver(signingKeyResolver)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException ex) {
            throw new TokenValidationException(ex.getMessage());
        }
    }
}
