package com.vesko.chatserver.service;

import com.vesko.chatserver.entity.Message;
import com.vesko.chatserver.repository.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService implements IMessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }
}
