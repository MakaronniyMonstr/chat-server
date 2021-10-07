package com.vesko.chatserver.service;

import com.vesko.chatserver.entity.Room;
import com.vesko.chatserver.entity.RoomMember;
import com.vesko.chatserver.entity.User;
import com.vesko.chatserver.repository.RoomMemberRepository;
import org.springframework.stereotype.Service;

@Service
public class RoomMemberService implements IRoomMemberService {
    private final RoomMemberRepository roomMemberRepository;

    public RoomMemberService(RoomMemberRepository roomMemberRepository) {
        this.roomMemberRepository = roomMemberRepository;
    }

    @Override
    public RoomMember save(RoomMember roomMember) {
        return roomMemberRepository.save(roomMember);
    }

    @Override
    public boolean existsByUserAndRoom(User user, Room room) {
        return roomMemberRepository.existsByRoomAndUser(room, user);
    }
}
