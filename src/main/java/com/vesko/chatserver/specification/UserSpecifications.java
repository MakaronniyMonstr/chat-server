package com.vesko.chatserver.specification;

import com.vesko.chatserver.entity.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {
    public static Specification<User> usernameContains(String username) {
        return (book, cq, cb) -> {
            if (username != null) return cb.like(book.get("username"), "%" + username + "%");
            return cb.conjunction();
        };
    }
}
