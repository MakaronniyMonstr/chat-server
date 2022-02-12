package com.vesko.chatserver.repository;

import com.vesko.chatserver.entity.KeyBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KeyBoxRepository extends JpaRepository<KeyBox, Long> {
    Optional<KeyBox> findByUserUsername(String username);
}