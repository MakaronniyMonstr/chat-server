package com.vesko.chatserver.service.impl;

import com.google.crypto.tink.subtle.Base64;
import com.vesko.chatserver.entity.KeyBox;
import com.vesko.chatserver.entity.User;
import com.vesko.chatserver.exception.CipherException;
import com.vesko.chatserver.exception.ResourceNotFoundException;
import com.vesko.chatserver.repository.KeyBoxRepository;
import com.vesko.chatserver.service.IKeyBoxService;
import com.vesko.chatserver.service.IUserService;
import com.vesko.chatserver.utils.crypto.api.ICryptoManager;
import com.vesko.chatserver.utils.crypto.api.IKeyExchangeApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

@Service
public class KeyBoxService implements IKeyBoxService {
    private final IKeyExchangeApi keyExchangeApi;
    private final IUserService userService;
    private final KeyBoxRepository repository;
    private final ICryptoManager cryptoManager;

    @Autowired
    public KeyBoxService(IKeyExchangeApi keyExchangeApi,
                         IUserService userService,
                         KeyBoxRepository repository,
                         ICryptoManager cryptoManager) {
        this.keyExchangeApi = keyExchangeApi;
        this.userService = userService;
        this.repository = repository;
        this.cryptoManager = cryptoManager;
    }

    @Override
    public byte[] create(String username, String peersPublicKey) {
        byte[] publicKey;

        try {
            byte[] privateKey = keyExchangeApi.generatePrivateKey();
            byte[] sessionKey = keyExchangeApi.generateSessionKey(privateKey, Base64.decode(peersPublicKey));
            publicKey = keyExchangeApi.generatePublicKey(privateKey);

            User user = userService.findUserByUsername(username);
            user.getKeyBox().setSessionKey(Base64.encodeToString(sessionKey, Base64.DEFAULT));
        } catch (GeneralSecurityException e) {
            throw new CipherException(e.getMessage());
        }


        return publicKey;
    }

    @Override
    public String decryptText(String encryptedText) {
        byte[] text = Base64.decode(encryptedText);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        KeyBox keyBox = repository.findByUserUsername(user.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("No key box"));

        return new String(cryptoManager.decrypt(text, Base64.decode(keyBox.getSessionKey())));
    }

    @Override
    public String encryptText(String plainText) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        KeyBox keyBox = repository.findByUserUsername(user.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("No key box"));

        return Base64.encodeToString(cryptoManager.encrypt(plainText.getBytes(StandardCharsets.UTF_8), Base64.decode(keyBox.getSessionKey())), Base64.DEFAULT);
    }
}
