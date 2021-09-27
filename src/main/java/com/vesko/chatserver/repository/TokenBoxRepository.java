package com.vesko.chatserver.repository;

import com.vesko.chatserver.entity.TokenBox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenBoxRepository extends JpaRepository<TokenBox, Long> {
}
