package com.vesko.chatserver.service;

import com.vesko.chatserver.entity.Message;
import com.vesko.chatserver.entity.Room;
import com.vesko.chatserver.entity.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IMessageService {
    Message save(Message message);
}
