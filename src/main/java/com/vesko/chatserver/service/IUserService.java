package com.vesko.chatserver.service;

import com.vesko.chatserver.entity.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional()
public interface IUserService {
    @Transactional(readOnly = true)
    User findUserByUsername(String username);

    User save(User user);
}
