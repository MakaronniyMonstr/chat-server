package com.vesko.chatserver.service;

import com.vesko.chatserver.entity.Room;
import com.vesko.chatserver.exception.ResourceNotFoundException;
import com.vesko.chatserver.repository.RoomRepository;
import org.springframework.stereotype.Service;

@Service
public class RoomService implements IRoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room findRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

}
