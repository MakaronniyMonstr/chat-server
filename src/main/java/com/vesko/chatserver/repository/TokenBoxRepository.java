package com.vesko.chatserver.repository;

import com.vesko.chatserver.entity.TokenBox;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenBoxRepository extends JpaRepository<TokenBox, Long> {
    Optional<TokenBox> findTokenBoxByUserUsername(String username);
    Optional<TokenBox> findTokenBoxByUserUsernameAndAccess(String username, String accessToken);
    Optional<TokenBox> findTokenBoxByUserUsernameAndRefresh(String username, String refreshToken);
}
