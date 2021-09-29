package com.vesko.chatserver.controller;

import com.vesko.chatserver.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public List<UserDTO> findUserByCriteria(@RequestParam(required = false) String username) {
        return null;
    }
}
