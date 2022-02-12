package com.vesko.chatserver;

import com.google.crypto.tink.subtle.Base64;
import com.vesko.chatserver.utils.crypto.api.impl.CryptoManager;
import com.vesko.chatserver.utils.crypto.api.impl.KeyExchangeApi;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ChatServerApplicationTests {

	@Test
	void contextLoads() throws GeneralSecurityException {
		KeyExchangeApi keyExchangeApi = new KeyExchangeApi("secret");

		byte[] privateKey1 = keyExchangeApi.generatePrivateKey();
		byte[] privateKey2 = keyExchangeApi.generatePrivateKey();

		byte[] publicKey1 = keyExchangeApi.generatePublicKey(privateKey1);
		byte[] publicKey2 = keyExchangeApi.generatePublicKey(privateKey2);

		byte[] sessionKey1 = keyExchangeApi.generateSessionKey(privateKey1, publicKey2);
		byte[] sessionKey2 = keyExchangeApi.generateSessionKey(privateKey2, publicKey1);

		String b64SessionKey1 = Base64.encodeToString(sessionKey1, Base64.DEFAULT);
		String b64SessionKey2 = Base64.encodeToString(sessionKey2, Base64.DEFAULT);
		assertThat(b64SessionKey1, is(b64SessionKey2));

		assertThat(Base64.decode(b64SessionKey1), is(Base64.decode(b64SessionKey2)));

		String plainText = "text";
		CryptoManager cryptoManager = new CryptoManager();

		byte[] encryptedText = cryptoManager.encrypt(plainText.getBytes(StandardCharsets.UTF_8), sessionKey1);
		byte[] decryptedText = cryptoManager.decrypt(encryptedText, sessionKey1);

		assertThat(plainText, is(new String(decryptedText)));
	}

}
