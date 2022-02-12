package com.vesko.chatserver.service;

import org.mapstruct.Named;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IKeyBoxService {
    byte[] create(String username, String peersPublicKey);

    @Named("decryptText")
    String decryptText(String encryptedText);
    @Named("encryptText")
    String encryptText(String plainText);
}
