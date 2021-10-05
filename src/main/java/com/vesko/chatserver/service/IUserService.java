package com.vesko.chatserver.service;

import com.vesko.chatserver.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IUserService {
    @Transactional(readOnly = true)
    User findUserByUsername(String username);

    @Transactional(readOnly = true)
    Page<User> findUsersByCriteriaAndPage(Specification<User> specification, Pageable pageable);

    User save(User user);
}
