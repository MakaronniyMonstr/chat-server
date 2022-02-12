package com.vesko.chatserver.utils.crypto.api;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

public interface IKeyExchangeApi {
    byte[] generatePrivateKey();
    byte[] generatePublicKey(byte[] privateKey) throws InvalidKeyException;
    byte[] generateSessionKey(byte[] privateKey, byte[] peersPublicKey) throws GeneralSecurityException;
}
