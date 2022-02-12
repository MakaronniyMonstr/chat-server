package com.vesko.chatserver.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.vesko.chatserver.dto.UserDto;
import com.vesko.chatserver.dto.view.JsonPage;
import com.vesko.chatserver.dto.view.OutputViews;
import com.vesko.chatserver.service.impl.UserService;
import com.vesko.chatserver.specification.UserSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public JsonPage<UserDto> findUserByCriteria(Pageable pageable,
                                                @RequestParam(required = false) String username) {
        Page<UserDto> page = userService.findUsersByCriteriaAndPage(
                Specification
                        .where(UserSpecifications.usernameStartsWith(username)),
                pageable)
                .map(UserDto::new);

        return new JsonPage<>(
                page.getContent(),
                page.getPageable(),
                page.getTotalElements()
        );
    }
}
