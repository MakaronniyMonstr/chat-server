package com.vesko.chatserver.utils.crypto.api.impl;

import com.google.crypto.tink.subtle.Hkdf;
import com.google.crypto.tink.subtle.X25519;
import com.vesko.chatserver.utils.crypto.api.IKeyExchangeApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

@Component
public class KeyExchangeApi implements IKeyExchangeApi {
    private final byte[] secret;

    public KeyExchangeApi(@Value("${spring.crypto.secret}") String secret) {
        this.secret = secret.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public byte[] generatePrivateKey() {
        return X25519.generatePrivateKey();
    }

    @Override
    public byte[] generatePublicKey(byte[] privateKey) throws InvalidKeyException {
        return X25519.publicFromPrivate(privateKey);
    }

    @Override
    public byte[] generateSessionKey(byte[] privateKey, byte[] peersPublicKey) throws GeneralSecurityException {
        byte[] sharedKey = X25519.computeSharedSecret(privateKey, peersPublicKey);

        return Hkdf.computeHkdf(
                "HMACSHA256",
                sharedKey,
                secret,
                null,
                32);
    }
}
