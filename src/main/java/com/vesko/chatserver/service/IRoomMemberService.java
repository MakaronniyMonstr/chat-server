package com.vesko.chatserver.service;

import com.vesko.chatserver.entity.Room;
import com.vesko.chatserver.entity.RoomMember;
import com.vesko.chatserver.entity.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IRoomMemberService {
    RoomMember save(RoomMember roomMember);
    void remove(RoomMember roomMember);
}
