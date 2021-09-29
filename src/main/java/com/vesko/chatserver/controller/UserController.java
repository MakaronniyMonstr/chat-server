package com.vesko.chatserver.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.vesko.chatserver.dto.UserDTO;
import com.vesko.chatserver.dto.view.JsonPage;
import com.vesko.chatserver.dto.view.OutputViews;
import com.vesko.chatserver.entity.User;
import com.vesko.chatserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @JsonView(OutputViews.Detailed.class)
    public JsonPage<UserDTO> findUserByCriteria(Pageable pageable,
                                                @RequestParam(required = false) String username) {
        Specification<User> specification = (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (username != null) predicates.add(cb.like(root.get("username"), username + "%"));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
        Page<UserDTO> page = userService.findUsersByCriteriaAndPage(specification, pageable).map(UserDTO::new);

        return new JsonPage<>(
                page.getContent(),
                page.getPageable(),
                page.getTotalPages());
    }
}
