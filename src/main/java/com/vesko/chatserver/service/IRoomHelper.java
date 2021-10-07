package com.vesko.chatserver.service;

import com.vesko.chatserver.entity.*;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IRoomHelper {
    Room create(User user);
    Room addMember(Room room, User user, ERoomRole roomRole);
    Room removeMember(Room room, RoomMember roomMember);
    Room changeMember(Room room, RoomMember roomMember);

    Room addMessage(Room room, Message message);
    Message editMessage(Message message);
}
