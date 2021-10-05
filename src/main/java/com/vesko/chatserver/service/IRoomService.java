package com.vesko.chatserver.service;

import com.vesko.chatserver.entity.Room;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IRoomService {
    @Transactional(readOnly = true)
    Room findRoomById(Long id);

    Room save(Room room);
}
