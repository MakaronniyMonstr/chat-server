package com.vesko.chatserver.service;

import com.vesko.chatserver.entity.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class RoomHelper implements IRoomHelper {
    private final IRoomService roomService;
    private final IRoomMemberService roomMemberService;
    private final IMessageService messageService;

    public RoomHelper(IRoomService roomService,
                      IRoomMemberService roomMemberService, IMessageService messageService) {
        this.roomService = roomService;
        this.roomMemberService = roomMemberService;
        this.messageService = messageService;
    }

    @Override
    public Room create(User user) {
        Room room = new Room();
        room = roomService.save(room);

        return addMember(room, user, ERoomRole.ADMIN);
    }

    @PreAuthorize("@roomPermission.canWrite(#room, principal)")
    @Override
    public Room addMember(Room room, User user, ERoomRole roomRole) {
        RoomMember roomMember = new RoomMember(user, roomRole, room);
        roomMemberService.save(roomMember);

        return room;
    }

    @PreAuthorize("@roomPermission.canWrite(#room, principal)")
    @Override
    public Room removeMember(Room room, RoomMember roomMember) {
        roomMemberService.remove(roomMember);

        return room;
    }

    @PreAuthorize("@roomPermission.canWrite(#room, principal)")
    @Override
    public Room changeMember(Room room, RoomMember roomMember) {
        roomMemberService.save(roomMember);
        return room;
    }

    @PreAuthorize("@roomPermission.isMember(#room, principal)")
    @Override
    public Room addMessage(Room room, Message message) {
        message = messageService.save(message);
        message.setRoom(room);
        message.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        return room;
    }

    @PreAuthorize("@roomPermission.isMember(#room, principal) &&" +
            "@messagePermission.canWrite(#room, principal)")
    @Override
    public Message editMessage(Message message) {
        return messageService.save(message);
    }


}
