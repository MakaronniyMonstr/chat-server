package com.vesko.chatserver.service;

import com.vesko.chatserver.entity.Message;
import com.vesko.chatserver.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IMessageService {
    Message save(Message message);
    Message create(String username, Message message);

    @Transactional(readOnly = true)
    Page<Message> findAllByPage(Pageable pageable);
}
