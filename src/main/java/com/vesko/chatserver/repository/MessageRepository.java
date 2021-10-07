package com.vesko.chatserver.repository;

import com.vesko.chatserver.entity.Message;
import com.vesko.chatserver.entity.Room;
import com.vesko.chatserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Optional<Message> findByRoomAndUser(Room room, User user);
}
