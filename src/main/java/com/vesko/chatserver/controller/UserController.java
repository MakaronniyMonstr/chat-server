package com.vesko.chatserver.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.vesko.chatserver.dto.UserDTO;
import com.vesko.chatserver.dto.view.InputViews;
import com.vesko.chatserver.dto.view.OutputViews;
import com.vesko.chatserver.entity.User;
import com.vesko.chatserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder encoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }


    @GetMapping
    public List<UserDTO> findUserByCriteria(@RequestParam(required = false) String username) {
        return null;
    }

    @PostMapping("/register")
    @JsonView({OutputViews.Detailed.class})
    public UserDTO registerUser(@Validated(InputViews.New.class) @RequestBody UserDTO userDTO) {
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        User user = userService.save(userDTO.toEntity());
        return new UserDTO(user);
    }
}
