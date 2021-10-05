package com.vesko.chatserver.service;

import com.vesko.chatserver.entity.RoomMember;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IRoomMemberService {
    RoomMember save(RoomMember roomMember);
}
