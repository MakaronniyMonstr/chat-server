package com.vesko.chatserver.utils.crypto.api.impl;

import com.vesko.chatserver.exception.CipherException;
import com.vesko.chatserver.utils.crypto.api.ICryptoManager;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
public class CryptoManager implements ICryptoManager {

    public static final String CIPHER = "AES/GCM/NoPadding";
    public static final int IV_SIZE = 12;
    public static final int TAG_SIZE = 128;
    private final Cipher cipher;

    public CryptoManager() throws NoSuchPaddingException, NoSuchAlgorithmException {
        this.cipher = Cipher.getInstance(CIPHER);
    }

    @Override
    public byte[] encrypt(byte[] plainText, byte[] key) {
        byte[] cipherText;

        try {
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"));

            byte[] iv = cipher.getIV();
            cipherText = new byte[IV_SIZE + cipher.getOutputSize(plainText.length)];

            System.arraycopy(iv, 0, cipherText, 0, IV_SIZE);
            cipher.doFinal(plainText, 0, plainText.length, cipherText, IV_SIZE);

            return cipherText;
        } catch (InvalidKeyException | ShortBufferException | IllegalBlockSizeException | BadPaddingException e) {
            throw new CipherException(e.getMessage());
        }
    }

    @Override
    public byte[] decrypt(byte[] cipherText, byte[] key) {
        try {
            cipher.init(Cipher.DECRYPT_MODE,
                    new SecretKeySpec(key, "AES"),
                    new GCMParameterSpec(TAG_SIZE, cipherText, 0, IV_SIZE));

            return cipher.doFinal(cipherText, IV_SIZE, cipherText.length - IV_SIZE);
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
            throw new CipherException(e.getMessage());
        }
    }
}
