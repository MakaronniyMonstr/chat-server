package com.vesko.chatserver.service;

import com.vesko.chatserver.entity.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class RoomHelper implements IRoomHelper {
    private final IRoomService roomService;
    private final IRoomMemberService roomMemberService;


    public RoomHelper(IRoomService roomService,
                      IRoomMemberService roomMemberService) {
        this.roomService = roomService;
        this.roomMemberService = roomMemberService;
    }

    @Override
    public Room create(User user) {
        Room room = new Room();
        room = roomService.save(room);

        return addMember(room, user, ERoomRole.ADMIN);
    }

    @Override
    public Room addMember(Room room, User user, ERoomRole roomRole) {
        RoomMember roomMember = new RoomMember(user, roomRole, room);
        roomMemberService.save(roomMember);

        return room;
    }

    @Override
    public Room removeMember(Room room, User user) {
        room.getRoomMembers().removeIf(roomMember -> roomMember.getUser().equals(user));
        return room;
    }

    @Override
    public Room addMessage(Room room, Message message) {
        return null;
    }
}
