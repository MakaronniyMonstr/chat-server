package com.vesko.chatserver.service;

import com.vesko.chatserver.entity.ERoomRole;
import com.vesko.chatserver.entity.Message;
import com.vesko.chatserver.entity.Room;
import com.vesko.chatserver.entity.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IRoomHelper {
    Room create(User user);
    Room addMember(Room room, User user, ERoomRole roomRole);
    Room removeMember(Room room, User user);

    Room addMessage(Room room, Message message);
    Room editMessage(Room room, Message message);
}
