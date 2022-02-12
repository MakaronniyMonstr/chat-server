package com.vesko.chatserver.utils.crypto.api;

public interface ICryptoManager {
    byte[] encrypt(byte[] plainText, byte[] key);
    byte[] decrypt(byte[] cipherText, byte[] key);
}
