package com.vesko.chatserver.service.impl;

import com.vesko.chatserver.entity.Message;
import com.vesko.chatserver.entity.User;
import com.vesko.chatserver.repository.MessageRepository;
import com.vesko.chatserver.service.IMessageService;
import com.vesko.chatserver.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MessageService implements IMessageService {
    private final MessageRepository messageRepository;
    private final IUserService userService;

    @Autowired
    public MessageService(MessageRepository messageRepository, IUserService userService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
    }

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Message create(String username, Message message) {
        User user = userService.findUserByUsername(username);
        message.setUser(user);
        message = save(message);

        return message;
    }

    @Override
    public Page<Message> findAllByPage(Pageable pageable) {
        return messageRepository.findAll(pageable);
    }
}
