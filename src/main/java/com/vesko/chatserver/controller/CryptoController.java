package com.vesko.chatserver.controller;

import com.google.crypto.tink.subtle.Base64;
import com.vesko.chatserver.dto.KexBoxDto;
import com.vesko.chatserver.entity.User;
import com.vesko.chatserver.service.IKeyBoxService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/crypto")
public class CryptoController {
    private final IKeyBoxService keyBoxService;

    public CryptoController(IKeyBoxService keyBoxService) {
        this.keyBoxService = keyBoxService;
    }

    @PostMapping("/handshake")
    public Map<String, String> handshake(@AuthenticationPrincipal User user,
                                         @RequestBody KexBoxDto kexBoxDto) {
        byte[] publicKey = keyBoxService.create(user.getUsername(), kexBoxDto.getPublicKey());

        return Collections.singletonMap("publicKey", Base64.encodeToString(publicKey, Base64.DEFAULT));
    }
}
