package com.vesko.chatserver.permission;

import com.vesko.chatserver.entity.Message;
import com.vesko.chatserver.entity.Room;
import com.vesko.chatserver.entity.User;
import com.vesko.chatserver.exception.ActionNotPermittedException;
import com.vesko.chatserver.repository.MessageRepository;
import org.springframework.stereotype.Component;

@Component
public class MessagePermission {
    private final MessageRepository messageRepository;

    public MessagePermission(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public boolean canWrite(Room room, User user) {
        Message message = messageRepository.findByRoomAndUser(room, user)
                .orElseThrow(ActionNotPermittedException::new);
        return true;
    }

    public boolean canRead(Room room, User user) {
        return canWrite(room, user);
    }
}
