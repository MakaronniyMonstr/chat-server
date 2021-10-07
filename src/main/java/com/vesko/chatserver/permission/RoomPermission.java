package com.vesko.chatserver.permission;

import com.vesko.chatserver.entity.ERoomRole;
import com.vesko.chatserver.entity.Room;
import com.vesko.chatserver.entity.RoomMember;
import com.vesko.chatserver.entity.User;
import com.vesko.chatserver.exception.ActionNotPermittedException;
import com.vesko.chatserver.repository.RoomMemberRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Component
public class RoomPermission {
    private final List<ERoomRole> editRoles = List.of(
            ERoomRole.ADMIN
    );

    private final List<ERoomRole> readRoles = List.of(
            ERoomRole.values()
    );

    private final RoomMemberRepository roomMemberRepository;

    public RoomPermission(RoomMemberRepository roomMemberRepository) {
        this.roomMemberRepository = roomMemberRepository;
    }

    public boolean canWrite(Room room, User user) {
        RoomMember roomMember = roomMemberRepository.findByRoomAndUser(room, user)
                .orElseThrow(ActionNotPermittedException::new);
        return editRoles.contains(roomMember.getRole());
    }

    public boolean isMember(Room room, User user) {
        roomMemberRepository.findByRoomAndUser(room, user)
                .orElseThrow(ActionNotPermittedException::new);
        return true;
    }

    public boolean canRead(Room room, User user) {
        RoomMember roomMember = roomMemberRepository.findByRoomAndUser(room, user)
                .orElseThrow(ActionNotPermittedException::new);
        return readRoles.contains(roomMember.getRole());
    }
}
