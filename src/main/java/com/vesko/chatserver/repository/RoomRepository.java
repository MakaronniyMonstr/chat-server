package com.vesko.chatserver.repository;

import com.vesko.chatserver.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
