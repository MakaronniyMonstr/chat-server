package com.vesko.chatserver.specification;

import com.vesko.chatserver.entity.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {
    public static Specification<User> usernameStartsWith(String username) {
        return (book, cq, cb) -> cb.like(book.get("username"), username + "%");
    }

    /*
            Specification<User> specification = (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.like(root.get("username"), username + "%"));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
     */
}
