package com.vesko.chatserver.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.vesko.chatserver.dto.UserDTO;
import com.vesko.chatserver.dto.view.InputViews;
import com.vesko.chatserver.dto.view.OutputViews;
import com.vesko.chatserver.entity.TokenBox;
import com.vesko.chatserver.entity.User;
import com.vesko.chatserver.service.ITokenBoxService;
import com.vesko.chatserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping()
public class AuthController {
    private final UserService userService;
    private final ITokenBoxService tokenBoxService;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(UserService userService,
                          ITokenBoxService tokenBoxService,
                          PasswordEncoder encoder,
                          AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.tokenBoxService = tokenBoxService;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    @JsonView({OutputViews.Detailed.class})
    public UserDTO registerUser(@Validated(InputViews.New.class) @RequestBody UserDTO userDTO) {
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        User user = userService.save(userDTO.toEntity());

        return new UserDTO(user);
    }

    @PostMapping("/authenticate")
    @JsonView({OutputViews.Detailed.class})
    public Map<String, String> authenticateUser(@Validated(InputViews.New.class) @RequestBody UserDTO userDTO) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userDTO.getUsername(),
                    userDTO.getPassword()
            ));
        } catch (final BadCredentialsException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        TokenBox tokenBox = tokenBoxService.obtainTokenBox(userDTO.getUsername());
        return Map.of(
                "access", tokenBox.getAccessToken(),
                "refresh", tokenBox.getRefreshToken());
    }
}
