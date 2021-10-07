package com.vesko.chatserver.repository;

import com.vesko.chatserver.entity.Room;
import com.vesko.chatserver.entity.RoomMember;
import com.vesko.chatserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface RoomMemberRepository extends JpaRepository<RoomMember, Long>, JpaSpecificationExecutor<RoomMember> {
    Optional<RoomMember> findByRoomAndUser(Room room, User user);
}
