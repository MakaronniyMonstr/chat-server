package com.vesko.chatserver.service;

import com.vesko.chatserver.entity.Message;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IMessageService {
    Message save(Message message);
}
